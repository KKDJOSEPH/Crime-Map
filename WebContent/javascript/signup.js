const divflag = document.querySelector(".containAlert");

async function sendflag(){
	const nm = document.getElementsByName("userName")[0].value;
	const fn = document.getElementsByName("firstName")[0].value;
	const ln = document.getElementsByName("lastName")[0].value;
	const pw1 = document.getElementsByName("passWord")[0].value;
	const pw2 = document.getElementsByName("passWord2")[0].value;
	const userinfo = {
		userName: nm,
		firstName: fn,
		lastName: ln,
		passWord1: pw1,
		passWord2: pw2
	};
	if(nm === "" || fn === "" || ln === ""){
		const divName = document.createElement("div");
		divName.textContent = "Name is Empty";
		divName.className = "alert alert-danger alert-dismissible fade show";
		divName.role = "alert";
		divflag.appendChild(divName);
		window.setTimeout(function(){
			divflag.removeChild(divName);
		}, 2000);
	}else if(pw1 === "" || pw2 === ""){
		const divName = document.createElement("div");
		divName.textContent = "Password is empty";
		divName.className = "alert alert-danger alert-dismissible fade show";
		divName.role = "alert";
		divflag.appendChild(divName);
		window.setTimeout(function(){
			divflag.removeChild(divName);
		}, 2000);
	}
	else if(pw1 !== pw2){
		const divName = document.createElement("div");
		divName.textContent = "Passwords are not same";
		divName.className = "alert alert-danger alert-dismissible fade show";
		divName.role = "alert";
		divflag.appendChild(divName);
		window.setTimeout(function(){
			divflag.removeChild(divName);
		}, 2000);
	} else{
			console.log(userinfo);
			$.ajax({
				type: "post",
			    dataType: "text",
			    url: "signup",	
			    data: {
			    	userName: nm,
			    	firstName: fn,
			    	lastName: ln,
			    	passWord1: pw1,
			    	passWord2: pw2
			    },	
			    success: function (msg) {
			    	console.log("success in ajax");
			    	const divName = document.createElement("div");

					if(msg == "1"){
						divName.className = "alert alert-success alert-dismissible fade show";
						divName.textContent = "Sign in successful";
					}
					else{
						console.log("FLAG IS HERE  ", msg);
						divName.className = "alert alert-danger alert-dismissible fade show";
						divName.textContent = "User name is already taken";
						/*
						try to add some html directly:
						const temp = document.createElement("div");
						const trytext = "<div class=\"alert alert-primary\" role=\"alert\">\n" +
							"  A simple primary alert—check it out!\n" +
							"</div>";
						temp.innerHTML = trytext;
						*/
					}
				divName.role = "alert";
				divflag.appendChild(divName);
				window.setTimeout(function(){
					divflag.removeChild(divName);
				}, 2000);
				if(msg == "1") window.location.href = "Signin.html";
		       },
		       error:function(err){
		    	   console.log("error in ajax");
		    	   console.log(err);
		    	   console.log(err.responseText);
		       }
			  });
		}
}

function test(){
	const btn = document.getElementById("button");
	if(btn == null){
		console.log("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwhyyyyyyyyyyyyyyyyyyyyyyyyy");
	}
	btn.addEventListener("click", sendflag);
}
test();