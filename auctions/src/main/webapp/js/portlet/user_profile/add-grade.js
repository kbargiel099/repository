var submitUrl = jQuery("#addGradeUrl").val();
var returnUrl = jQuery("#returnUrl").val();

jQuery(document).ready(function(){
	jQuery('.selectpicker').selectpicker();	
});

jQuery("#submit").click(function(){
	if(jQuery("#add-grade-form").valid()){
		var params = new Params().
			push('grade', JSON.stringify(jQuery("#add-grade-form").serializeObject())).
			getArray();
		
		submit(params);
	}
});

function submit(params_){
	sendRequestParams(submitUrl, params_, onResponse);
}

var onResponse = function(res){
	if(JSON.parse(res.success) == true){
		window.location.href = buildUrl(returnUrl,'message',Liferay.Language.get('grade.has.been.added'));
	}else{
		responsiveNotify(Liferay.Language.get('error.msg'));
	}
};

jQuery(function() {
	  jQuery("#add-grade-form").validate({
	    rules: {
	    	auctionId: {
	        required: true
	      },
	      grade: {
	        required: true
	      },
	      comment: {
		    required: true
		  }
	    }
	  });
	});