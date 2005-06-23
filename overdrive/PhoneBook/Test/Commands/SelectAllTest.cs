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
using System.Collections;
using Nexus.Core;
using Nexus.Core.Helpers;
using NUnit.Framework;

namespace PhoneBook.Core.Commands
{
	/// <summary>
	/// Exercise SelectAll Command per [OVR-5].
	/// </summary>
	/// 
	[TestFixture]
	public class SelectAllTest : BaseTest
	{
		/// <summary>
		/// Assert result of SelectAll, after another method runs the command.
		/// </summary>
		/// <param name="context">Context with result to assert.</param>	
		/// 	
		private void SelectAll_Result (IRequestContext context)
		{
			IList list = AssertListOutcome (context);
			IDictionary row = list [0] as IDictionary;
			Assert.IsNotNull (row, "Expected list entry to be an IDictionary.");
			string[] KEYS = {App.FIRST_NAME, App.LAST_NAME, App.USER_NAME, App.EXTENSION, App.HIRED, App.HOURS, App.EDITOR};
			bool valid = true;
			foreach (string key in KEYS)
			{
				valid = valid && row.Contains (key);
			}
			Assert.IsTrue (valid, "Expected row to contain all keys.");
		}


		/// <summary>
		/// Filter all and succeed, using catalog.
		/// </summary>
		/// 
		[Test]
		public void SelectAll_Pass ()
		{
			IRequestContext context = catalog.ExecuteRequest (App.FILTER);
			SelectAll_Result (context);
		}

		[Test]
		public void FilterHelper_Format ()
		{
			IViewHelper helper = catalog.GetHelper ("directory_find_helper");
			helper.Execute ();
			AppContextList list = helper.Outcome as AppContextList;
			// AppContextList list = helper.Context.Criteria["filter"] as AppContextList;
			Assert.IsNotNull (list, "Expected list to be AppContextList");
			AppContext row = list [0] as AppContext;
			Assert.IsNotNull (row, "Expected rows to be AppContexts");

			string hired = row.hired;
			Assert.IsNotNull (hired, "Expected each row to have a hired date.");
			Assert.IsTrue (hired.Length < "##/##/#### ".Length, hired + ": Expected short date format.");

			string extension = row.extension;
			Assert.IsNotNull (extension, "Expected each row to have an extension.");
			Assert.IsTrue (extension.Length > "1234567890".Length, extension + ": Expected formatted extension.");

		}
	}
}