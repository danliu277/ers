//Script have username already there if cookie exists
// return cookie value
function setUser(){
	var value = "; " + document.cookie;
	var parts = value.split("; " + "user" + "=");
	if (parts.length == 2) return parts.pop().split(";").shift();
}
var name = setUser();
// set user
document.getElementById('user').setAttribute('value', !name || name == 'undefined' ? "" : name);