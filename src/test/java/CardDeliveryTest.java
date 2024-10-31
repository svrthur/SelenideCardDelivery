import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.SetValueOptions.withText;


public class CardDeliveryTest {

    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
    @Test
    public void validTest(){


        open ( "http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] input") .doubleClick();
        $("[data-test-id='date'] input").sendKeys(generateDate(4, "dd.MM.yyyy"));
        $("[name = 'name']").setValue("Иван Иванов");
        $("[name = 'phone']").setValue("+79123456789");
        $("[data-test-id= 'agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=notification]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно забронирована на " + generateDate(4, "dd.MM.yyyy") ));





    }
}
