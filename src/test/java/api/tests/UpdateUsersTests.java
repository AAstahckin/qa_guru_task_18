package api.tests;

import api.models.*;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static api.responseassertions.AssertionsResponseUpdateUsers.assertUpdateUserTestApi;
import static api.service.RequestUpdateUser.sendUpdateUser;
import static api.utils.RandomUtils.getRandomText;

@Story("Изменение пользователя")
@DisplayName("Изменение пользователя API PUT /users/id")
public class UpdateUsersTests extends TestBase {

    CreateUsersBodyModel bodyModel = new CreateUsersBodyModel();
    static Faker faker = new Faker();

    @Test
    @Tag("sanity")
    @DisplayName("Изменение пользователя")
    @Description("ПозитивнВ сценарий")
    public void positiveUpdateUserTest() {
        bodyModel.setName(faker.name().firstName()).setJob(faker.artist().name());
        val response = sendUpdateUser(bodyModel, faker.random().nextInt(1,100));
        assertUpdateUserTestApi(response, bodyModel);

    }


    @DisplayName("Негативный сценарий изменения пользователя ")
    @Description("Негативный сценарии")
    @ParameterizedTest(name = "[Name = {0}, Job = {1}]")
    @MethodSource("checkOutputParamsForPage")
    public void negativeUpdateUserTests(String valueName,String valueJob) {
        bodyModel.setName(valueName).setJob(valueJob);
        val response = sendUpdateUser(bodyModel, faker.random().nextInt(1,100));
        assertUpdateUserTestApi(response, bodyModel);
    }

    private static Stream<Arguments> checkOutputParamsForPage() {
        return Stream.of(
                Arguments.of("!@%&^%*(!@(&*", "()*^*&%&*^@)"),
                Arguments.of("КУАГУРУСУПЕР", "КУАГУРУСУПЕР"),
                Arguments.of("qa.guru super", "qa.guru super"),
                Arguments.of(" ", " "),
                Arguments.of(getRandomText(100), getRandomText(100)),
                Arguments.of(null, null),
                Arguments.of(null, faker.job().position()),
                Arguments.of(faker.job().position(), null),
                Arguments.of("4124123", "41251253"));
    }
}
