/**
 * @license
 * Copyright Color-Coding Studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */
/// <reference path="../../3rdparty/ibas/index.d.ts" />
/// <reference path="../../3rdparty/openui5/index.d.ts" />
/// <reference path="../../index.d.ts" />
/// <reference path="./organizationalstructure/index.ts" />
/// <reference path="./ownership/index.ts" />
/// <reference path="./role/index.ts" />
namespace organizedroles {
    export namespace ui {
        /**
         * 视图导航
         */
        export class Navigation extends ibas.ViewNavigation {

            /**
             * 创建实例
             * @param id 应用id
             */
            protected newView(id: string): ibas.IView {
                let view: ibas.IView = null;
                switch (id) {
                    case app.OrganizationalStructureListApp.APPLICATION_ID:
                        view = new c.OrganizationalStructureListView();
                        break;
                    case app.OrganizationalStructureChooseApp.APPLICATION_ID:
                        view = new c.OrganizationalStructureChooseView();
                        break;
                    case app.OrganizationalStructureEditApp.APPLICATION_ID:
                        view = new c.OrganizationalStructureEditView();
                        break;
                    case app.OwnershipListApp.APPLICATION_ID:
                        view = new c.OwnershipListView();
                        break;
                    case app.OwnershipChooseApp.APPLICATION_ID:
                        view = new c.OwnershipChooseView();
                        break;
                    case app.OwnershipEditApp.APPLICATION_ID:
                        view = new c.OwnershipEditView();
                        break;
                    case app.RoleListApp.APPLICATION_ID:
                        view = new c.RoleListView();
                        break;
                    case app.RoleChooseApp.APPLICATION_ID:
                        view = new c.RoleChooseView();
                        break;
                    case app.RoleEditApp.APPLICATION_ID:
                        view = new c.RoleEditView();
                        break;
                    default:
                        break;
                }
                return view;
            }
        }
    }
}