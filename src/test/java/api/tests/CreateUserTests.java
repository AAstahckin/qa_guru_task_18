package api.tests;

import api.models.CreateUsersBodyModel;
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
import static api.responseassertions.AssertionsResponseCreateUserApi.assertPositiveCreateUserApi;
import static api.service.RequestCreateUser.sendCreateUser;
import static api.utils.RandomUtils.getRandomText;

@Story("Создание юзера")
@DisplayName("Создание юзера API POST /users")
public class CreateUserTests extends TestBase {

    CreateUsersBodyModel userBody = new CreateUsersBodyModel();
    static Faker faker = new Faker();

    @Test
    @DisplayName("Проверка создания пользователя")
    @Description("Позитивный сценарий")
    @Tag("sanity")
    public void positiveCreateUserTest() {
        userBody.setName(faker.name().firstName()).setJob(faker.job().position());
        val response = sendCreateUser(userBody);
        assertPositiveCreateUserApi(response, userBody);

    }

    @DisplayName("Негативный сценарий создания пользователя")
    @Description("Негативный сценарий")
    @ParameterizedTest(name = "[user: {0}; pass:{1}]")
    @MethodSource("submitIncorrectParameters")
    public void negativeLoginTest(String name, String job) {
        userBody.setName(name).setJob(job);
        val response = sendCreateUser(userBody);
        assertPositiveCreateUserApi(response, userBody);
    }

    private static Stream<Arguments> submitIncorrectParameters() {
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
