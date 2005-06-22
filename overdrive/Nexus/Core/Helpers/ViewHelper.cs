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
using Nexus.Core.Tables;

namespace Nexus.Core.Helpers
{
	/// <summary>
	/// Standard implementation of IViewHelper.
	/// </summary>
	/// 
	public abstract class ViewHelper : IViewHelper
	{
		/// <summary>
		/// Default setting for ListSuffix ["_list"].
		/// </summary>
		public const string LIST_SUFFIX = "_list";

		/// <summary>
		/// Default setting for NullIfEmpty [true].
		/// </summary>
		public const bool NULL_IF_EMPTY = true;

		/// <summary>
		/// Default setting for Prefix [""].
		/// </summary>
		public const string PREFIX = "";

		/// <summary>
		/// Default setting for SelectItemPrompt ["--v--"].
		/// </summary>
		public const string SELECT_ITEM_PROMPT = "--v--";

		#region Read and Bind (abstract)

		public abstract void ExecuteBind (ICollection controls);

		public abstract void ReadExecute (ICollection controls);

		public abstract void Bind (ICollection controls);

		public abstract void Read (ICollection controls);

		public void Execute ()
		{
			Catalog.ExecuteView (Context);
		}

		#endregion

		#region Messages (abstract) 

		public abstract string ErrorsText { get; }

		public abstract string HintsText { get; }

		#endregion

		#region Read and Bind 

		private IRequestContext _Context;
		/// <remarks>
		/// The Context is obtained through reference to the Catalog and Command.
		/// All other properties and methods of the Helper refer to the Context, 
		/// making Context the cornerstone property.
		/// </remarks>
		/// 
		public IRequestContext Context
		{
			get
			{
				if (_Context == null)
					_Context = Catalog.GetRequest (Command);
				return _Context;
			}
		}


		public IList Outcome
		{
			get
			{
				IList result = null;
				object o = Context.Criteria[Context.Command];
				if (o == null)
				{
					result = new ArrayList (1);
					result.Add (Context.Criteria);
				}
				else
				{
					result = o as IList;
					if (result == null)
					{
						result = new ArrayList (1);
						result.Add (o);
					}
				}
				return result;
			}
		}

		#endregion

		#region Messages

		public IDictionary Alerts
		{
			get { return Context.Alerts; }
		}

		public bool HasAlerts
		{
			get { return Context.HasAlerts; }
		}

		public Exception Fault
		{
			get { return Context.Fault; }
		}

		public bool HasFault
		{
			get { return Context.HasFault; }
		}

		public bool IsNominal
		{
			get { return (!HasAlerts && !HasFault); }
		}


		public IDictionary Hints
		{
			get { return Context.Hints; }
		}

		public bool HasHints
		{
			get { return Context.HasHints; }
		}

		#endregion 

		#region Tables

		public IFieldTable FieldTable
		{
			get { return Context.FieldTable; }
		}

		private IList _FieldSet;
		public IList FieldSet
		{
			get { return _FieldSet; }
			set { _FieldSet = value; }
		}

		#endregion 

		#region Options

		private string _Prefix = PREFIX;
		public string Prefix
		{
			get { return _Prefix; }
			set { _Prefix = value; }
		}

		private string _ListSuffix = LIST_SUFFIX;
		public string ListSuffix
		{
			get { return _ListSuffix; }
			set { _ListSuffix = value; }
		}

		private bool _NullIfEmpty = NULL_IF_EMPTY;
		public bool NullIfEmpty
		{
			get { return _NullIfEmpty; }
			set { _NullIfEmpty = value; }
		}

		private string _SelectItemPrompt = SELECT_ITEM_PROMPT;
		public string SelectItemPrompt
		{
			get { return _SelectItemPrompt; }
			set { _SelectItemPrompt = value; }
		}

		#endregion

		#region Properties

		private IRequestCatalog _Catalog;
		/// <summary>
		/// Provide the application object catalog for this Helper.
		/// </summary>
		/// <remarks>
		/// The Catalog is usually set through dependency injection. 
		/// The Catalog and Command must be set before calling other methods.
		/// </remarks>
		/// 
		public IRequestCatalog Catalog
		{
			get { return _Catalog; }
			set { _Catalog = value; }
		}

		private IRequestCommand _Command;
		/// <summary>
		/// Provide the command for this Helper.
		/// </summary>
		/// <remarks>
		/// The Command is usually set through dependency injection. 
		/// The Catalog and Command must be set before calling other methods.
		/// </remarks>
		/// 
		public IRequestCommand Command
		{
			get { return _Command; }
			set { _Command = value; }
		}

		#endregion 
	}
}