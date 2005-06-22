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

namespace PhoneBook.Core.Commands
{
	/// <summary>
	/// Execute database statement for QueryID, 
	/// convert list returned to an AppContextList,  
	/// and place converted list in context under ID.
	/// </summary>
	/// 
	public class BaseList : AppCommand
	{
		public override bool RequestExecute (IRequestContext context)
		{
			IList rows = Mapper ().QueryForList (QueryID, context);
			AppContextList list = new AppContextList (rows);
			context [ID] = list;
			return CONTINUE;
		}
	}
}