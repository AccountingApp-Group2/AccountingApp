// Require the modules.
var gulp = require("gulp")
var gutil = require("gulp-util")
var minimist = require("minimist")
var config = require("./config.json")

var options = minimist(process.argv.slice(2))

// Global Variables
global.myLayout = options.Layout
global.myLayoutName = options.LayoutName
global.config = config
global.pugSrc = ["*.pug", "!**/template.pug"]
global.dashboardRename = ""
global.rtl = ""
global.menuActiveColor = "white"
global.menuToggle = "ft-toggle-left"
global.menuDataToggle = "expanded"
global.sidebarBgColor = "man-of-steel"
global.skSidebarBgImg = "../../../app-assets/img/sidebar-bg/01.jpg"
global.sidebarBgImg = "../../../app-assets/img/sidebar-bg/01.jpg"

if (options.TextDirection !== undefined) {
  global.myTextDirection = options.TextDirection.toLowerCase()
  if (myTextDirection == "rtl") rtl = "-rtl"
} else {
  global.myTextDirection = ""
}

gutil.log(gutil.colors.green("Starting Gulp!!"))

// Exclude template specific files
// if (myLayout == 'vertical-menu-template') {
dashboardRename = config.vertical_menu_template.dashboardRename
pugSrc = config.vertical_menu_template.pugSrc
// }

if (myLayout == "vertical-menu-dark-template") {
  sidebarBgColor = "black"
}

if (myLayout == "vertical-menu-transparent-template") {
  sidebarBgColor = ""
  sidebarBgImg = ""
}

const autoPrefixTasks = require("./gulp-tasks/autoprefix")(gulp)
const cleanTasks = require("./gulp-tasks/clean")(gulp)
const copyTask = require("./gulp-tasks/copy")(gulp)
const cssTasks = require("./gulp-tasks/css")(gulp)
const scssTasks = require("./gulp-tasks/scss")(gulp)
const uglifyTasks = require("./gulp-tasks/uglify")(gulp)
const notifyTasks = require("./gulp-tasks/notify")(gulp)

// Clean CSS & JS
gulp.task("dist-clean", cleanTasks.css, cleanTasks.js)

// Dist HTML
gulp.task("dist-html", gulp.series(cleanTasks.html))

// Monitor Changes
gulp.task("monitor", gulp.series(scssTasks.watch))
// Dist JS
gulp.task(
  "dist-js",
  gulp.series(cleanTasks.js, copyTask.js, uglifyTasks.js, notifyTasks.js)
)

// SASS Compile
gulp.task(
  "sass-compile",
  gulp.parallel(
    scssTasks.main,
    scssTasks.core,
    scssTasks.pages,
    scssTasks.plugins,
    scssTasks.themes,
    scssTasks.style
  )
)
// SASS Compile RTL
gulp.task("sass-compile-rtl", scssTasks.rtl)

// CSS Distribution Task.
gulp.task(
  "dist-css",
  gulp.series(
    cleanTasks.css,
    "sass-compile",
    autoPrefixTasks.css,
    cssTasks.css_comb,
    cssTasks.css_min,
    gulp.parallel(notifyTasks.css)
  )
)

// RTL CSS Distribution Task.
gulp.task(
  "dist-css-rtl",
  gulp.series(
    cleanTasks.css_rtl,
    "sass-compile",
    "sass-compile-rtl",
    cssTasks.css_rtl,
    autoPrefixTasks.css_rtl,
    cssTasks.css_rtl_comb,
    cssTasks.css_min
  )
)

// DEFAULT
gulp.task("dist", gulp.parallel("dist-css", "dist-js"))

gulp.task("default", gulp.parallel("dist-css", "dist-js"))
