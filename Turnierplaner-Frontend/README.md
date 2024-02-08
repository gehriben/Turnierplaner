# Runnable Version
Node v8.10.0  
NPM 5.6.0

# AngularJS Material-Start (ES6)
[![Gitter](https://badges.gitter.im/angular/material2.svg)](https://gitter.im/angular/material?utm_source=badge&utm_medium=badge)

This branch contains the final/complete version (i.e. `step-10-finished`) of the
[Material Start ES6 Tutorial](https://github.com/angular/material-start/tree/es6-tutorial) branch
in this repository. 

You can see the [Live Demo here](https://angularjs-material-start.firebaseapp.com/).

There are many additional branches in this repository that you may find useful:

 - [`master`](https://github.com/angular/material-start/tree/master) (this branch) - A copy of the
 `es6` branch outlined below with additional notes about the available branches.
 - [`es5-tutorial`](https://github.com/angular/material-start/tree/es5-tutorial) - Step-by-step
 instructions that clearly demonstrate how the Starter application can be created in minutes using
 ES5. 
 - [`es5`](https://github.com/angular/material-start/tree/es5) - The final ES5 version which you
 complete in the last step of the tutorials above.
 - [`es6-tutorial`](https://github.com/angular/material-start/tree/es6-tutorial) - Step-by-step
 instructions that clearly demonstrate how the Starter application can be created in minutes using
 ES6. 
 - [`es6`](https://github.com/angular/material-start/tree/es6) - The final ES6 version which you
 complete in the last step of the tutorials above.
 - [`typescript`](https://github.com/angular/material-start/tree/typescript) - The final Starter
 Application built using Typescript.
 
> **Note:** We do not currently offer a `typescript-tutorial` branch as the steps are fairly similar
  to the existing `es6-tutorial` branch.

#### Purpose

This project uses the latest master branch of AngularJS Material to build the application outlined
below.

![material-starter-ux2](https://cloud.githubusercontent.com/assets/6004537/14996543/b588eb46-1137-11e6-803c-ce23996c9742.png)

Above is a snapshot of the Starter-App with a **Master-Detail** layout: showing a list of users
(left) and a user detail view (right).

Also shown is the user experience that will be displayed for smaller device sizes. The responsive
layout reveals the **menu** button that can be used to hide the user list. And the **share** button
can be used to show the Share bottom sheet view.

This Starter app demonstrates how:

*  AngularJS Material `layout` and `flex` options can easily configure HTML containers
*  AngularJS Material components `<md-toolbar>`, `<md-sidenav>`, and `<md-icon>` can quickly provide
   a base application structure
*  Custom controllers can be used and show `<md-bottomsheet>` with HTML templates
*  Custom controller can easily, and programmatically open/close the SideNav component
*  Responsive breakpoints and `$mdMedia` are used
*  Theming can be altered/configured using `$mdThemingProvider`


This sample application is purposed as both a learning tool and a skeleton application for a typical
[AngularJS Material](http://angularjs.org/) web app, comprised of a side navigation area and a
content area. You can use it to quickly bootstrap your angular webapp projects and dev environment
for these projects.

- - -

#### "How to build an App"

Here are some generalized steps that may be used to conceptualize the application implementation
process:

1. Plan your layout and the components you want to use

2. Use hard-coded HTML and mock content to make sure the components appear as desired

3. Wire components to your application logic

   > Use the seamless integration possible with AngularJS directives and controllers.<br/>
   > This integration assumes that you have unit tested your application logic.

4. Add Responsive breakpoints

5. Add Theming support

6. Confirm ARIA compliance

7. Write End-to-end (e2e) Tests

   > It is important to validate your application logic with AngularJS Material UI components.

###### Wireframe

The illustration below shows how we planned the layout and identified the primary components that
will be used in the Starter app:

<br/>
<img src="https://cloud.githubusercontent.com/assets/210413/6444676/c247c8f8-c0c4-11e4-8206-208f55cbceee.png">

> **Note:** The container #2 (above) is a simple `<div>` container and not an AngularJS Material
  component.

- - -

##### Getting Started

This project uses [jspm.io](http://jspm.io), a package manager for SystemJS which is built on top
of the dynamic ES6 module loader. This allows developers to load any module format (ES6, CommonJS,
AMD, and globals).

###### Prerequisites

This project assumes that you have NodeJS and any relevant development tools (like XCode) already
installed.
 
###### Getting Started

Clone this repository and execute the following commands in a terminal:

* `git checkout master`
* `npm install`
* `npm run serve`

> **Note:** Open the dev console to see any warnings and browse the elements.

###### Layout

You will notice a few files/directories within this project:

 1. `app/src` - This is where all of your application files are stored.
 2. `app/assets` - This folder contains some tutorial-provided images and icons which are used by
    the application.
 3. `index.html` - The entry point to your application. This uses System.js to load the
    `app/src/boot/boot.js` bootstrap file which in turn loads the `app/src/app.js` file that imports
     all of your dependencies and declares them as AngularJS modules, and configures the icons and
     theming for the application.

#### Troubleshooting

If you have issues getting the application to run or work as expected:

1. Make sure you have installed JSPM and run the `jspm update` command.
2. Reach out on our [Forum](https://groups.google.com/forum/#!forum/ngmaterial) to see if any other
   developers have had the same issue.
3. This project is based against the `master` branch of AngularJS Material, so it is always showing
   the latest and greatest. You may want to update the `package.json` to use Version 1.1.0 or
   another stable release to make sure it isn't because of something we changed recently.
4. Search for the issue here on [GitHub](https://github.com/angular/material-start/issues?q=is%3Aissue+is%3Aopen).
5. If you don't see an existing issue, please open a new one with the relevant information and the
   details of the problem you are facing.

##### Problems with NPM Version

NPM version >= 6.0.0 can cause problems during *npm install*.
Downgrade NPM to solve this. This can be done through [NVM](https://github.com/creationix/nvm/blob/master/README.md).

1. Downlaod NVM for Windows and install it (**must be placed directly in `C:/`**).
2. With `nvm list available` all available versions are displayed.
3. NPM 5.6.0 is e.g. in the Node version 8.10.0.
4. Use `nvm install 8.10.0` to install the mentioned version.
5. To actually use this version type `nvm use 8.10.0`.

##### Git not installed

If the following error occures ...

````PowerShell
err  Git not installed. You can install git from [http://git-scm.com/downloads](http://git-scm.com/downloads).
````

... but Git is installed. The most likely cause is that the folder where Git is installed
is not added to the enviromental variable `PATH`.

Add the folder to the `PATH` variable manually or run the installtion file from Git again.
When reinstalling Git make sure to select `Git from command line and also from 3rd-party software` in the appropriate step.


# SQL Dummy Data

[SQL-Dummy-Data](https://zhaw-my.sharepoint.com/personal/widtmbri_students_zhaw_ch/Documents/Forms/All.aspx?RootFolder=%2Fpersonal%2Fwidtmbri_students_zhaw_ch%2FDocuments%2FPSIT4%2FSQL%20Dummy%20Data&FolderCTID=0x01200083344153F8C09345BB8CB1C4027AFF1B&View=%7BFCD812B3-6E7E-43ED-B992-B369974EE001%7D)
