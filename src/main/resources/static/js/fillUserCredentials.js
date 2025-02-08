function populateFieldsAndSubmitUser(event) {
	event.preventDefault();
	
	document.getElementById("username").value = "utilizator";
	document.getElementById("password").value = "parola utilizator";
	
	document.getElementById("loginForm").submit();
}

function populateFieldsAndSubmitAdmin(event) {
	event.preventDefault();
	
	document.getElementById("username").value = "administrator";
	document.getElementById("password").value = "parola administrator";
	
	document.getElementById("loginForm").submit();
}
