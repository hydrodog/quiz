/**
 * This JavaScript is for the drawing questions
 * 
 * author: Yi Yang
 */

context = null;

	var rectV = false;
	var rectH = false;
	var wire = false;
	var wStart = false;

	var x = 0, y = 0;

	var rLength = 55;
	var rWidth = 20;

	window.onload = function(){
		var canvas = document.getElementById("breadBoard");
		context = canvas.getContext("2d");

		if(!canvas.getContext){
			alert("canvas not supported.");
			return;
		}

		context.fillStyle = "#777777";
		var xBorder = 25;
		var hole = 6;

		for(var i = 0; i < 10; i++){
			for(var j = 0; j < 5; j++){
				context.fillRect(xBorder+i*78+j*13,57,hole,hole)
			}
		}

		for(var i = 0; i < 5; i++){
			for(var j = 0; j < 59; j++){
				context.fillRect(xBorder+13*j, 100+19*i, hole, hole);
			}
		}

		for(var i = 0; i < 5; i++){
			for(var j = 0; j < 59; j++){
				context.fillRect(xBorder+13*j, 220+19*i, hole, hole);
			}
		}

		for(var i = 0; i < 10; i++){
			for(var j = 0; j < 5; j++){
				context.fillRect(xBorder+i*78+j*13,340,hole,hole)
			}
		}

		
		canvas.addEventListener('mousedown', doMouseDown, true);
		canvas.addEventListener('mousemove', doMouseMove, true);
		canvas.addEventListener('mouseup', doMouseUp, true);

		// canvas.focus();
		
	}

	function getPointOnCanvas(canvas, x, y) {
			var bbox = canvas.getBoundingClientRect();
			return { x: x - bbox.left * (canvas.width  / bbox.width),
					y: y - bbox.top  * (canvas.height / bbox.height)
					};
		}

	function drawRectH(){
		if(rectH){
			rectH = false;
		}else{
			rectH = true;
		}
		// alert(rectH);
		rectV = false;
		wire = false;
	}

	function drawRectV(){
		if(rectV){
			rectV = false;
		}else{
			rectV = true;
		}
		rectH = false;
		wire = false;
	}

	function drawWire(){
		if(wire){
			wire = false;
		}else{
			wire = true;
		}
		rectH = false;
		rectV = false;
		
	}


	function doMouseDown(event){
		var x = event.pageX;
		var y = event.pageY;
		var canvas = event.target;
		var loc = getPointOnCanvas(canvas, x, y);

		if(rectH){
			context.fillStyle = "#111111";
			context.fillRect(loc.x , loc.y - rWidth/2, rLength, rWidth);
		}

		if(rectV){
			context.fillStyle = "red";
			context.fillRect(loc.x - rWidth/2, loc.y, rWidth, rLength);
		}

		if(wire){
			if(!wStart){
				context.beginPath();
				context.moveTo(loc.x, loc.y);
				wStart = true;
			}else{
				context.lineTo(loc.x, loc.y);
				context.stroke();
				context.closePath();
				wStart = false;
			}
			// alert("wire: " + wire + "; wStart: " + wStart);
		}
	}

	// function doMouseMove(event){
	// 	var x = event.pageX;
	// 	var y = event.pageY;
	// 	var canvas = event.target;
	// 	var loc = getPointOnCanvas(canvas, x, y);

	// 	if(wire && wStart){
	// 		context.lineTo(loc.x, loc.y);
	// 		context.stroke();
	// 	}
	// }

	function doMouseUp(event){
		var x = event.pageX;
		var y = event.pageY;
		var canvas = event.target;
		var loc = getPointOnCanvas(canvas, x, y);

		// if(wStart){
		// 	// doMouseMove(event);
		// 	context.lineTo(loc.x, loc.y);
		// 	context.closePath();
		// 	wStart = false;
		// }
	}