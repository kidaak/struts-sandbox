namespace Nexus.Web
{
	/// <summary>
	/// Extend MessageLabel to respond only to the View_Hint event 
	/// and use an alternate suffix.
	/// </summary>
	public class HintLabel : MessageLabel
	{
		
		/// <summary>
		/// Default suffix for NameLabel IDs ["_hint"].
		/// </summary>
		public const string HINT_SUFFIX = "_hint";
		
		/// <summary>
		/// Set the defaults for this subclass.
		/// </summary>
		public HintLabel()
		{
			Suffix = HINT_SUFFIX;
			Resource = false;
			Required = false;
			View_Alert = false;
		}
	}
}

