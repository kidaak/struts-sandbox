<%@ Control Language="c#" AutoEventWireup="false" Codebehind="Lister2.ascx.cs" Inherits="PhoneBook.Web.Controls.Lister2" TargetSchema="http://schemas.microsoft.com/intellisense/ie5"%>
<asp:DataGrid id="list" Runat="server" AutoGenerateColumns=False>
	<HeaderStyle CssClass="HeaderStyle" BackColor="#CCCC99"></HeaderStyle>
	<AlternatingItemStyle CssClass="AlternatingItemStyle" BackColor="#CCCC99"></AlternatingItemStyle>
</asp:DataGrid>
<p><asp:Button ID="add" Runat="server"></asp:Button></p>
