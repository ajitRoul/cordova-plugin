var exec = require('cordova/exec');

exports.basicNotification = function(args, success, error) {
    exec(success, error, "AndroidWearNotification", "basicNotification", args);
};
