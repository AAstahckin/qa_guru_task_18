package api.responseassertions;

import api.models.getuser.UserResponseModel;
import api.models.getusers.UsersResponseModel;
import api.data.UsersDataValues;

import static api.constans.OtherTexts.TEXT_SUPPORT;
import static api.constans.OtherTexts.URL_SUPPORT;
import static api.helpers.CustomsTextsSteps.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertionsResponseGetUsersApi {

    public static void assertGetRandomUser(UsersResponseModel response, UsersDataValues randomUserId) {
        step(matchingParameterId(randomUserId.getId()), () ->
                assertEquals(response.getData().get(randomUserId.ordinal()).getId(), randomUserId.getId()));
        step(matchingParameter(randomUserId.getEmail()), () ->
                assertEquals(response.getData().get(randomUserId.ordinal()).getEmail(), randomUserId.getEmail()));
        step(matchingParameter(randomUserId.getFirstName()), () ->
                assertEquals(response.getData().get(randomUserId.ordinal()).getFirstName(), randomUserId.getFirstName()));
        step(matchingParameter(randomUserId.getLastName()), () ->
                assertEquals(response.getData().get(randomUserId.ordinal()).getLastName(), randomUserId.getLastName()));
        step(matchingParameter(randomUserId.getAvatar()), () ->
                assertEquals(response.getData().get(randomUserId.ordinal()).getAvatar(), randomUserId.getAvatar()));

    }

    public static void assertGetUser(UserResponseModel response, UsersDataValues randomUserId) {
        step(matchingParameterId(randomUserId.getId()), () ->
                assertEquals(response.getDataUserResponseModel().getId(), randomUserId.getId()));
        step(matchingParameter(randomUserId.getEmail()), () ->
                assertEquals(response.getDataUserResponseModel().getEmail(), randomUserId.getEmail()));
        step(matchingParameter(randomUserId.getFirstName()), () ->
                assertEquals(response.getDataUserResponseModel().getFirstName(), randomUserId.getFirstName()));
        step(matchingParameter(randomUserId.getLastName()), () ->
                assertEquals(response.getDataUserResponseModel().getLastName(), randomUserId.getLastName()));
        step(matchingParameter(randomUserId.getAvatar()), () ->
                assertEquals(response.getDataUserResponseModel().getAvatar(), randomUserId.getAvatar()));
        step(support(URL_SUPPORT.getValue()), () ->
                assertEquals(response.getUserSupportResponseModel().getUrl(), URL_SUPPORT.getValue()));
        step(support(TEXT_SUPPORT.getValue()), () ->
                assertEquals(response.getUserSupportResponseModel().getText(), TEXT_SUPPORT.getValue()));
    }

    public static void assertGetElementPage(UsersResponseModel response, int page, int count) {
        step(countElement(count), () ->
                assertEquals(response.getData().size(), count));
        step(countPage(page), () ->
                assertEquals(response.getPage(), page));
    }

    public static void assertGetElementPerPage(UsersResponseModel response, UsersDataValues usersDataValues) {
        step(countElement(usersDataValues.getId()), () ->
                assertEquals(response.getData().size(), usersDataValues.getId()));
        step(countPerPage(usersDataValues.getId()), () ->
                assertEquals(response.getPerPage(), usersDataValues.getId()));
        step(support(URL_SUPPORT.getValue()), () ->
                assertEquals(response.getSupport().getUrl(), URL_SUPPORT.getValue()));
        step(support(TEXT_SUPPORT.getValue()), () ->
                assertEquals(response.getSupport().getText(), TEXT_SUPPORT.getValue()));
    }

}
