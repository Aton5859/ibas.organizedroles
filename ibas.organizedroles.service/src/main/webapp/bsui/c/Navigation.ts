/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import * as ibas from "ibas/index";
import * as organizationalstructureApps from "../../bsapp/organizationalstructure/index";
import * as ownershipApps from "../../bsapp/ownership/index";
import * as roleApps from "../../bsapp/role/index";
import * as organizationalstructureViews from "./organizationalstructure/index";
import * as ownershipViews from "./ownership/index";
import * as roleViews from "./role/index";

/**
 * 视图导航
 */
export default class Navigation extends ibas.ViewNavigation {

    /**
     * 创建实例
     * @param id 应用id
     */
    protected newView(id: string): ibas.IView {
        let view: ibas.IView = null;
        switch (id) {
            case organizationalstructureApps.OrganizationalStructureListApp.APPLICATION_ID:
                view = new organizationalstructureViews.OrganizationalStructureListView();
                break;
            case organizationalstructureApps.OrganizationalStructureChooseApp.APPLICATION_ID:
                view = new organizationalstructureViews.OrganizationalStructureChooseView();
                break;
            case organizationalstructureApps.OrganizationalStructureEditApp.APPLICATION_ID:
                view = new organizationalstructureViews.OrganizationalStructureEditView();
                break;
            case ownershipApps.OwnershipListApp.APPLICATION_ID:
                view = new ownershipViews.OwnershipListView();
                break;
            case ownershipApps.OwnershipChooseApp.APPLICATION_ID:
                view = new ownershipViews.OwnershipChooseView();
                break;
            case ownershipApps.OwnershipEditApp.APPLICATION_ID:
                view = new ownershipViews.OwnershipEditView();
                break;
            case roleApps.RoleListApp.APPLICATION_ID:
                view = new roleViews.RoleListView();
                break;
            case roleApps.RoleChooseApp.APPLICATION_ID:
                view = new roleViews.RoleChooseView();
                break;
            case roleApps.RoleEditApp.APPLICATION_ID:
                view = new roleViews.RoleEditView();
                break;
            default:
                break;
        }
        return view;
    }
}
