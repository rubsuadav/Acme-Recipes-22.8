<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.roles.Epicure,acme.roles.Chef"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
      		<acme:menu-suboption code="77925380T: Suárez David, Rubén" action="https://github.com"/>
      		<acme:menu-separator/>
      		<acme:menu-suboption code="master.menu.anonymous.user-account.list-all-user-accounts" action="/any/user-account/list-all-user-accounts"/>
      		<acme:menu-suboption code="master.menu.anonymous.peep.list-recent" action="/any/peep/list-recent"/>
      		<acme:menu-suboption code="master.menu.anonymous.item.list" action="/any/item/list"/>
      		<acme:menu-suboption code="master.menu.anonymous.recipe.list" action="/any/recipe/list"/>

		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.user-account.list-all-user-accounts" action="/any/user-account/list-all-user-accounts"/>
			<acme:menu-suboption code="master.menu.authenticated.peep.list-recent" action="/any/peep/list-recent"/>
			<acme:menu-suboption code="master.menu.authenticated.item.list" action="/any/item/list"/>
			<acme:menu-suboption code="master.menu.authenticated.recipe.list" action="/any/recipe/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.bulletin.list-recent" action="/authenticated/bulletin/list-recent"/>
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

		<acme:menu-option code="master.menu.epicure" access="hasRole('Epicure')">
			<acme:menu-suboption code="master.menu.epicure.my-fine-dishes" action="/epicure/fine-dish/list-mine"/>
			<acme:menu-suboption code="master.menu.epicure.my-memorandums" action="/epicure/memorandum/list-mine"/>	
			<acme:menu-suboption code="master.menu.epicure.epicure-dashboard" action="/epicure/epicure-dashboard/show"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.chef" access="hasRole('Chef')">
			<acme:menu-suboption code="master.menu.chef.my-items" action="/chef/item/list-mine-items"/>
			<acme:menu-suboption code="master.menu.chef.recipe.list-mine-recipes" action="/chef/recipe/list-mine-recipes"/>
			<acme:menu-suboption code="master.menu.chef.my-fine-dishes" action="/chef/fine-dish/list-mine"/>
			<acme:menu-suboption code="master.menu.chef.my-memorandums" action="/chef/memorandum/list-mine"/>
		</acme:menu-option>
		
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-epicure" action="/authenticated/epicure/create" access="!hasRole('Epicure')"/>
			<acme:menu-suboption code="master.menu.user-account.epicure" action="/authenticated/epicure/update" access="hasRole('Epicure')"/>
			<acme:menu-suboption code="master.menu.user-account.become-chef" action="/authenticated/chef/create" access="!hasRole('Chef')"/>
			<acme:menu-suboption code="master.menu.user-account.chef" action="/authenticated/chef/update" access="hasRole('Chef')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>