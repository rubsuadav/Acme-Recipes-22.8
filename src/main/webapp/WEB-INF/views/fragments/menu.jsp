<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.roles.Patron,acme.roles.Inventor"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
      		<acme:menu-suboption code="45972333W: Alvarez Campanón, Juan Jose" action="http://www.lsi.us.es"/>
			<acme:menu-suboption code="30238243M: Cordero Díaz, Jesus Javier" action="http://www.lsi.us.es"/>
			<acme:menu-suboption code="29552922S: Paz Rivera, Roberto" action="https://github.com"/>
			<acme:menu-suboption code="49129488Q: Romalde Dorado, Miguel Angel" action="https://github.com"/>
      		<acme:menu-suboption code="29566855X: Sánchez Mendoza, Nicolás" action="http://www.lsi.us.es"/>
      		<acme:menu-suboption code="77925380T: Suárez David, Rubén" action="https://github.com"/>
      		<acme:menu-separator/>
      		<acme:menu-suboption code="master.menu.anonymous.user-account.list-all-user-accounts" action="/any/user-account/list-all-user-accounts"/>
			<acme:menu-suboption code="master.menu.anonymous.chirp.list-recent" action="/any/chirp/list-recent"/>
			<acme:menu-suboption code="master.menu.anonymous.item.list" action="/any/item/list"/>
			<acme:menu-suboption code="master.menu.anonymous.toolkit.list" action="/any/toolkit/list"/>

		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.user-account.list-all-user-accounts" action="/any/user-account/list-all-user-accounts"/>
			<acme:menu-suboption code="master.menu.authenticated.chirp.list-recent" action="/any/chirp/list-recent"/>
			<acme:menu-suboption code="master.menu.authenticated.item.list" action="/any/item/list"/>
			<acme:menu-suboption code="master.menu.authenticated.toolkit.list" action="/any/toolkit/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.announcement.list-recent" action="/authenticated/announcement/list-recent"/>
			<acme:menu-suboption code="master.menu.authenticated.system-configuration" action="/authenticated/system-configuration/show"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.announcement.list.button.create" action="/administrator/announcement/create"/>
			<acme:menu-suboption code="master.menu.administrator.administrator-dashboard" action="/administrator/administrator-dashboard/show"/>
			<acme:menu-suboption code="master.menu.administrator.system-configuration" action="/administrator/system-configuration/show"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">	
			<acme:menu-suboption code="master.menu.patron.my-patronage-reports" action="/patron/patronage-report/list-mine"/>
			<acme:menu-suboption code="master.menu.patron.my-patronages" action="/patron/patronage/list-mine"/>	
			<acme:menu-suboption code="master.menu.patron.patron-dashboard" action="/patron/patron-dashboard/show"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.inventor" access="hasRole('Inventor')">
			<acme:menu-suboption code="master.menu.inventor.my-items" action="/inventor/item/list-mine-items"/>
			<acme:menu-suboption code="master.menu.inventor.my-patronage-reports" action="/inventor/patronage-report/list-mine"/>
			<acme:menu-suboption code="master.menu.inventor.my-patronages" action="/inventor/patronage/list-mine"/>
			<acme:menu-suboption code="master.menu.inventor.toolkit.list-mine-toolkits" action="/inventor/toolkit/list-mine-toolkits"/>
		</acme:menu-option>
		
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-patron" action="/authenticated/patron/create" access="!hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.user-account.patron" action="/authenticated/patron/update" access="hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.user-account.become-inventor" action="/authenticated/inventor/create" access="!hasRole('Inventor')"/>
			<acme:menu-suboption code="master.menu.user-account.inventor" action="/authenticated/inventor/update" access="hasRole('Inventor')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>