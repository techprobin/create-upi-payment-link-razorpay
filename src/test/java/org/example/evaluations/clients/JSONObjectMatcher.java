package org.example.evaluations.clients;

import org.json.JSONObject;
import org.mockito.ArgumentMatcher;

public class JSONObjectMatcher implements ArgumentMatcher<JSONObject> {
    private final String expectedDescription;
    private final Double expectedAmount;
    private final String expectedName;

    private final String expectedEmail;

    private final String expectedPhoneNumber;

    private final Boolean expectedUpiLinkFlag;
    private final Boolean expectedNotifyViaSms;

    private final Boolean expectedNotifyViaEmail;

    private final String expectedPolicyName;


    public JSONObjectMatcher(String expectedDescription, Double expectedAmount,String expectedName,String expectedEmail,String expectedPhoneNumber,Boolean expectedUpiLinkFlag,Boolean expectedNotifyViaSms,Boolean expectedNotifyViaEmail,String expectedPolicyName) {
        this.expectedDescription = expectedDescription;
        this.expectedAmount = expectedAmount;
        this.expectedName = expectedName;
        this.expectedEmail = expectedEmail;
        this.expectedNotifyViaSms = expectedNotifyViaSms;
        this.expectedNotifyViaEmail = expectedNotifyViaEmail;
        this.expectedPolicyName = expectedPolicyName;
        this.expectedUpiLinkFlag = expectedUpiLinkFlag;
        this.expectedPhoneNumber = expectedPhoneNumber;
    }

    @Override
    public boolean matches(JSONObject jsonObject) {
        if (jsonObject == null) {
            return false;
        }

        return expectedName.equals(jsonObject.optJSONObject("customer").optString("contact", ""))
                && expectedEmail.equals(jsonObject.optJSONObject("customer").optString("email", ""))
                && expectedPhoneNumber.equals(jsonObject.optJSONObject("customer").optString("name", ""))
                && expectedAmount.equals(jsonObject.optDouble("amount"))
                && expectedDescription.equals(jsonObject.optString("description"))
                && expectedUpiLinkFlag.equals(jsonObject.optBoolean("upi_link"))
                && expectedNotifyViaSms.equals(jsonObject.optJSONObject("notify").optBoolean("sms"))
                && expectedNotifyViaEmail.equals(jsonObject.optJSONObject("notify").optBoolean("email"))
                && expectedPolicyName.equals(jsonObject.optJSONObject("notes").optString("policy_name",""));
    }
}