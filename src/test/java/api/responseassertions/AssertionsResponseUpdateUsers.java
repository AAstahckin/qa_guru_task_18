package api.responseassertions;

import api.models.CreateUsersBodyModel;
import api.models.UpdateUserResponseModel;

import static api.helpers.CustomsTextsSteps.matchingParameter;
import static api.utils.DateTimeCheck.timeDifferenceCreateForServ;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertionsResponseUpdateUsers {

    public static void assertUpdateUserTestApi(UpdateUserResponseModel response, CreateUsersBodyModel body) {
        step(matchingParameter(body.getName()), () ->
                assertEquals(response.getName(), body.getName()));
        step(matchingParameter(body.getJob()), () ->
                assertEquals(response.getJob(), body.getJob()));
        timeDifferenceCreateForServ(response.getUpdatedAt());
    }

}
