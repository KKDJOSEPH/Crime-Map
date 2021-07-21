const showUserProfile = async () => {
	  const myProfile = document.querySelector('#myProfile');
	  const nm = sessionStorage.getItem('userName');
	  console.log("now user name is:   " + nm);
		$.ajax({
			type: "post",
		    dataType: "json",
		    url: "profile",
		    data: {
		    	userName: nm
		    },	
		    success: function (msg) {
		    	console.log(msg);
		    	const firstNameRow = document.createElement('div');
		    	firstNameRow.className =
		    		'row info-row align-items-center justify-content-center';
		    	const firstNameLeftCol = document.createElement('div');
		    	firstNameLeftCol.className = 'col-5';
		    	firstNameLeftCol.innerHTML = '<p>' + 'First Name: ' + '</p>';
		    	firstNameRow.appendChild(firstNameLeftCol);
		    	const firstNameRightCol = document.createElement('div');
		    	firstNameRightCol.className = 'col-7';
		    	firstNameRightCol.innerHTML = '<p>' + msg.firstName + '</p>';
		    	firstNameRow.appendChild(firstNameRightCol);
		    	myProfile.appendChild(firstNameRow);
			
		    	const lastNameRow = document.createElement('div');
		    	lastNameRow.className =
		    		'row info-row align-items-center justify-content-center';
		    	const lastNameLeftCol = document.createElement('div');
		    	lastNameLeftCol.className = 'col-5';
		    	lastNameLeftCol.innerHTML = '<p>' + 'Last Name: ' + '</p>';
		    	lastNameRow.appendChild(lastNameLeftCol);
		    	const lastNameRightCol = document.createElement('div');
		    	lastNameRightCol.className = 'col-7';
		    	lastNameRightCol.innerHTML = '<p>' + msg.lastName + '</p>';
		    	lastNameRow.appendChild(lastNameRightCol);
		    	myProfile.appendChild(lastNameRow);
			
		    	const buttonRow = document.createElement('div');
		    	buttonRow.className = 'form-group row align-items-center justify-content-center';
		    	buttonRow.innerHTML =
		    		'<button type = "button" class="btn btn-dark" id = "button" onclick="changepage()"> Change your information </button>';
		    	myProfile.appendChild(buttonRow);
				       },
				       error:function(err){
				    	   console.log("error in ajax");
				    	   console.log(err);
				    	   console.log(err.responseText);
				       }
					  });
};
function test(){
	showUserProfile();
	const btn = document.querySelector('button');
	if(btn == null){
		console.log("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwhyyyyyyyyyyyyyyyyyyyyyyyyy");
	}
	btn.addEventListener("click", window.location.href = "UpdateProfile.html");
}
test();
