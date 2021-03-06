/**
  1. 유저 프로파일 페이지
  (1) 유저 프로파일 페이지 구독하기, 구독취소
  (2) 구독자 정보 모달 보기
  (3) 구독자 정보 모달에서 구독하기, 구독취소
  (4) 유저 프로필 사진 변경
  (5) 사용자 정보 메뉴 열기 닫기
  (6) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
  (7) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달 
  (8) 구독자 정보 모달 닫기
 */

// (1) 유저 프로파일 페이지 구독하기, 구독취소
function toggleSubscribe(toUserId, obj) {
	
	alert("aa");
	
	alert("toggleSubscribe"+toUserId);
	
	if ($(obj).text() === "구독취소") {
		$.ajax({
			type:"DELETE",
			url: "/api/subscribe/"+toUserId,
			dataType:"json"
		}).done(res=>{
			$(obj).text("구독하기");
			$(obj).toggleClass("blue");
		}).fail(error=>{
			console.log("구독취소 실패", error);
		});
		
		/*$(obj).text("구독하기");
		$(obj).toggleClass("blue");*/
	} else {
		$.ajax({
			type:"post",
			url: "/api/subscribe/"+toUserId,
			dataType:"json",
		}).done(resp=>{
			$(obj).text("구독취소");
			$(obj).toggleClass("blue");
		}).fail(error=>{
			console.log("구독하기 실패",error);
		});
		/*$(obj).text("구독취소");
		$(obj).toggleClass("blue");*/
	}
}

// (2) 구독자 정보  모달 보기
function subscribeInfoModalOpen(toUserId) {
	$(".model-subscribe").css("display", "flex");
	//자바스크립트 화살표 함수
	//https://velog.io/@ki_blank/JavaScript-%ED%99%94%EC%82%B4%ED%91%9C-%ED%95%A8%EC%88%98Arrow-function
	
	//alert(pageUserId);
	
	let userId = $("#userId").val();
	
	$.ajax({
		url:`/api/user/${toUserId}/subscribe`,
	}).done(res=>{
		$("subscribeMoalList").empty();
		
		/*console.log(res.data.length);*/
		
		
		res.data.forEach((u)=>{ 
			let item = getSubscribeModalItem(u);
			$("#subscribeModalList").append(item);
		});
	}).fail(error=>{
		console.log("구독정보 불러오기 오류" + error.text);
	});
	//$(".modal-subscribe").css("display", "flex");
}

/*// (2) 구독자 정보  모달 보기
function subscribeInfoModalOpen(pageUserId) {
	$(".model-subscribe").css("display", "flex");
	//자바스크립트 화살표 함수
	//https://velog.io/@ki_blank/JavaScript-%ED%99%94%EC%82%B4%ED%91%9C-%ED%95%A8%EC%88%98Arrow-function
	
	alert(pageUserId);
	
	$.ajax({
		url:`/api/user/${pageUserId}/subscribe`,
		type: "get",
		dataType:"json"
	}).done(res=>{
		console.log(res.data.length);
		
		
		
		res.data.forEach((u)=>{ 
			let item = getSubscribeModalItem(u);
			$("#subscribeModalList").append(item);
		});
	}).fail(error=>{
		console.log("구독정보 불러오기 오류", error);
	});
	//$(".modal-subscribe").css("display", "flex");
}*/

function getSubscribeModalItem(u) {
	let item = `<div class="subscribe__item" id="subscribeModalItem-${u.pageUserId}">`;
	item += `<div class="subscribe__img">`;
	item += `<img src="/upload/${u.postImageUrl}" alt=""  onerror="this.src='/images/person.jpeg'"/>`;
	item += `</div>`;
	item += `<div class="subscribe__text">`;
	item += `<h2>${u.username}</h2>`;
	item += `</div>`;
	item += `<div class="subscribe__btn">`;
	if (!u.equalState) {
		if (u.subscribeState) {
			item += `<button class="cta blue" onclick="toggleSubscribeModal(${u.pageUserId}, this)">구독취소</button>`;
		} else {
			item += `<button class="cta" onclick="toggleSubscribeModal(${u.pageUserId}, this)">구독하기</button>`;
		}
	}
	item += `</div>`;
	item += `</div>`;

	return item;
}

/*function getSubscribeModalItem(u) {
	
	alert(u.id);
	
	alert("구독 리스트 실행되나???????????????????");
	
	let item = '<div class="subscribe__item" id="subscribeModalItem-${u.id}">';
	item +='<div class="subscribe__img"> <img src="/upload/${u.profileImageUrl}" onerror="this.src=/images/person.jpeg"/></div>';
	item +='<div class="subscribe__text"> <h2>${u.username}</h2></div>';
	item +='<div class="subscribe__btn">';	
			
	if(!u.equalUserState){ //동일 유저가 아닐때 버튼이 만들어져야됨
		if(u.subscribeState){ //구독한 상태
			item +='<button class="cta blue" onclick="toggleSubscribe(${u.id},this)">구독취소</button>';
		}else{ //구독안한 상태
			item += '<button class="cta" onclick="toggleSubscribe(${u.id},this)">구독하기</button>';
	}		
}
		<button class="cta blue" onclick="toggleSubscribeModal(this)">구독취소</button>
			
	item +='</div> </div>';
	
	console.log(item);
	
	return item;
}*/


/*// (3) 구독자 정보 모달에서 구독하기, 구독취소
function toggleSubscribeModal(obj) {
	if ($(obj).text() === "구독취소") {
		$(obj).text("구독하기");
		$(obj).toggleClass("blue");
	} else {
		$(obj).text("구독취소");
		$(obj).toggleClass("blue");
	}
}*/

// (3) 유저 프로파일 사진 변경 (완)
function profileImageUpload() {
	$("#userProfileImageInput").click();

	$("#userProfileImageInput").on("change", (e) => {
		let f = e.target.files[0];

		if (!f.type.match("image.*")) {
			alert("이미지를 등록해야 합니다.");
			return;
		}

		// 사진 전송 성공시 이미지 변경
		let reader = new FileReader();
		reader.onload = (e) => {
			$("#userProfileImage").attr("src", e.target.result);
		}
		reader.readAsDataURL(f); // 이 코드 실행시 reader.onload 실행됨.
	});
}


// (4) 사용자 정보 메뉴 열기 닫기
function popup(obj) {
	$(obj).css("display", "flex");
}

function closePopup(obj) {
	$(obj).css("display", "none");
}


// (5) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
function modalInfo() {
	$(".modal-info").css("display", "none");
}

// (6) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달
function modalImage() {
	$(".modal-image").css("display", "none");
}

// (7) 구독자 정보 모달 닫기
function modalClose() {
	$(".modal-subscribe").css("display", "none");
	location.reload();
}






