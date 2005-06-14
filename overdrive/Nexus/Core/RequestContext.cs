/*
 * Copyright 2005 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
using System;
using System.Collections;
using Agility.Core;
using Nexus.Core.Tables;

namespace Nexus.Core
{
	/// <summary>
	/// Concrete IRequestContext implementation.
	/// </summary>
	/// 
	public class RequestContext : Context, IRequestContext
	{
		/// <summary>
		/// Convenience constructor to set Command on instantiation.
		/// </summary>
		/// <param name="command">Name of Command processing this Context.</param>
		/// 
		public RequestContext (string command)
		{
			Command = command;
		}

		/// <summary>
		/// Default, no argument constructor.
		/// </summary>
		/// 
		public RequestContext ()
		{
		}

		public string Command
		{
			get { return this [Tokens.Command] as string; }
			set { this [Tokens.Command] = value; }
		}

		public IRequestCommand CommandBin
		{
			get { return this [Tokens.CommandBin] as IRequestCommand; }
			set { this [Tokens.CommandBin] = value; }
		}

		public IFieldTable FieldTable
		{
			get { return this [Tokens.FieldTable] as IFieldTable; }
			set { this [Tokens.FieldTable] = value; }
		}

		public IList FieldSet
		{
			get { return this [Tokens.FieldSet] as IList; }
			set { this [Tokens.FieldSet] = value; }
		}

		public bool HasOutcome
		{
			get { return Contains (Command); }
		}

		public object Outcome
		{
			get { return this [Command]; }
			set { this [Command] = value; }
		}

		/// <summary>
		/// Convenience method to lazily instantiate a message store.
		/// </summary>
		/// <param name="template">Message template to add to the queue.</param>
		/// <param name="queue">Token for queue of messages within the 
		/// store.</param>
		/// <param name="key">Token for message store.</param>
		/// 
		private void AddStore (string template, string queue, string key)
		{
			IContext store = this [key] as IContext;
			if (null == store)
			{
				store = new Context (); // ISSUE: Spring?
				this [key] = store;
			}
			IList list;
			if (store.Contains (queue))
				list = store [queue] as IList;
			else
			{
				list = new ArrayList (); // ISSUE: Spring?
				store [queue] = list;
			}
			list.Add (template);
		}

		public IDictionary Alerts
		{
			get { return this [Tokens.Alerts] as IDictionary; }
			set { this [Tokens.Alerts] = value; }
		}

		public void AddAlert (string template)
		{
			AddStore (template, Tokens.GenericMessage, Tokens.Alerts);
		}

		public bool HasAlerts
		{
			get { return this.ContainsKey (Tokens.Alerts); }
		}

		public Exception Fault
		{
			get { return this [Tokens.Fault] as Exception; }
			set
			{
				Exception e = value as Exception;
				this [Tokens.Fault] = e;
				AddAlert (e.Message);
			}
		}

		public bool HasFault
		{
			get { return this.ContainsKey (Tokens.Fault); }
		}

		public bool IsNominal
		{
			get { return (!HasAlerts && !HasFault); }
		}

		public IDictionary Hints
		{
			get { return this [Tokens.Hints] as IDictionary; }
			set { this [Tokens.Hints] = value; }
		}

		public void AddHint (string template)
		{
			AddStore (template, Tokens.GenericMessage, Tokens.Hints);
		}

		public void AddHint (string template, string queue)
		{
			AddStore (template, queue, Tokens.Hints);
		}

		public bool HasHints
		{
			get { return this.ContainsKey (Tokens.Hints); }
		}

	}
}