package api.responseassertions;

import api.models.CreateUserResponseModel;
import api.models.CreateUsersBodyModel;

import static api.utils.DateTimeCheck.timeDifferenceCreateForServ;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AssertionsResponseCreateUserApi {

    public static void assertPositiveCreateUserApi(CreateUserResponseModel response, CreateUsersBodyModel userBody) {
        step("Проверяем что " + userBody.getName() + " присутствует в ответе ", () ->
                assertEquals(response.getName(), userBody.getName()));
        step("Проверяем что " + userBody.getJob() + " присутствует в ответе ", () ->
                assertEquals(response.getJob(), userBody.getJob()));
        step("Проверяем что " + response.getId() + " присутствует в ответе", () ->
                assertNotNull(response.getId()));
        timeDifferenceCreateForServ(response.getCreatedAt());
    }

}