/* 
 * Function to validate login credentials and to submit login form.
 *  */
function submitLoginForm(formId, buttonType) {
    var formObj = document.getElementById(formId);
    document.getElementById("userButtonType").value = buttonType;
    if (formObj.email == null || formObj.email.value.length == 0) {
        alert("Please enter email address.");
        return false;
    }

    if (formObj.password == null || formObj.password.value.length == 0)
    {
        alert("Please enter password.");
        return false;
    }

    if (validateEmail(formObj.email.value)) {
        // formObj.action = url;
        formObj.submit();
    } else {
        alert("Please enter valid email address.");
    }
}

//function to validate email address format.
function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

// Function to take action for drop down selection change..
function selectionChange(optionVal) {
    //Loop to disable ans text boxes.
    for (i = 3; i > optionVal; i--) {
        //document.getElementById("ans" + i).value = "";
        document.getElementById("ans" + i).disabled = true;
    }
    //Loop to enable ans text boxes.

    for (i = 1; i <= optionVal; i++) {
        document.getElementById("ans" + i).disabled = false;
    }
}


function submitActionForm(formId, paramVal) {
    var formObj = document.getElementById(formId);
    //document.getElementById("actionParam").value = paramVal;
    formObj.actionParam.value = paramVal;
    formObj.submit();
}

function submitStudyControllerForm(formId, paramVal, actionUrl) {
    // alert(formId);
    // alert(actionUrl);
    var formObj = document.getElementById(formId);
    formObj.actionParam.value = paramVal;
    //alert(formObj.actionParam.value);
    formObj.action = actionUrl;
    formObj.submit();
}

//myStudies.jsp to start or stop.
function changeStatusForm(formId, paramVal, actionUrl, status) {
    // alert(paramVal);
    var formObj = document.getElementById(formId);
    formObj.actionParam.value = paramVal;
    //  alert(formObj.actionParam.value);
    formObj.action = actionUrl;
    formObj.submit();
}



