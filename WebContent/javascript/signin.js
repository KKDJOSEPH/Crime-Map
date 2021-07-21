const divflag = document.querySelector(".containAlert");

async function sendflag(){
	const nm = document.getElementsByName("userName")[0].value;
	const pw = document.getElementsByName("passWord")[0].value;
	const userinfo = {
		userName: nm,
		passWord: pw
	};
	if(nm === ""){
		const divName = document.createElement("div");
		divName.className = "alert alert-danger alert-dismissible fade show";
		divName.textContent = "Username is Empty";
		divName.role = "alert";
		divflag.appendChild(divName);
		window.setTimeout(function(){
			divflag.removeChild(divName);
		}, 2000);

	}else if(pw === ""){
		const divName = document.createElement("div");
		divName.className = "alert alert-danger alert-dismissible fade show";
		divName.textContent = "Password is Empty";
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
		    url: "signin",
		    data: {
		    	userName: nm,
		    	passWord: pw
		    },	
		    success: function (msg) {
		    	console.log("success in ajax");
		    	const divName = document.createElement("div");

				if(msg == "1"){
					sessionStorage.setItem('userName', nm);
					divName.className = "alert alert-success alert-dismissible fade show";
					divName.textContent = "Sign in successful";
				}
				else if(msg == "2"){
					console.log("FLAG IS HERE  ", msg);
					divName.className = "alert alert-danger alert-dismissible fade show";
					divName.textContent = "Wrong UserName";
				}
				else{
					console.log("FLAG IS HERE  ", msg);
					divName.className = "alert alert-danger alert-dismissible fade show";
					divName.textContent = "Wrong password";
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
			if(msg == "1") window.location.href = "UserPage.html";
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