var uglify = require("gulp-uglify")
var rename = require("gulp-rename")

module.exports = (gulp, callback) => {
  const uglifyJsTask = function() {
    return gulp
      .src(["**/*.js", "!**/*.min.js"], { cwd: config.destination.js })
      .pipe(uglify())
      .pipe(rename({ suffix: ".min" }))
      .pipe(gulp.dest(config.destination.js))
  }

  // ---------------------------------------------------------------------------
  // Exports

  return {
    js: uglifyJsTask
  }
}
