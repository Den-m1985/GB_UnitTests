import org.example.Calculator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class CalculatorTest {
    @Test
    void additionExpressionEvaluation() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calculation(2, 6, '+')).isEqualTo(8);
    }

    @Test
    void subtractionExpressionEvaluation() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calculation(2, 2, '-')).isEqualTo(0);
    }

    @Test
    void multiplicationExpressionEvaluation() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calculation(2, 7, '*')).isEqualTo(14);
    }

    @Test
    void divisionExpressionEvaluation() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calculation(100, 10, '/')).isEqualTo(10);
    }

    @Test
    void expectedIllegalStateExceptionOnInvalidOperatorSymbol() {
        Calculator calculator = new Calculator();
        assertThatThrownBy(() ->
                calculator.calculation(8, 4, '_'))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void getOperandCompletesCorrectlyWithNumbers() {
        String testedValue = "9"; // Значение для тестов
        ByteArrayInputStream in = new ByteArrayInputStream(testedValue.getBytes());
        InputStream inputStream = System.in; // Сохраняем ссылкуна ввод с клавиатуры
        System.setIn(in); // Подменяем ввод
        Calculator.getOperand(); // Вызываем метод
        System.out.println(testedValue); // Для наглядности вывода
        System.setIn(inputStream); // Подменяем обратно
    }

    @Test
    void getOperandCompletesCorrectlyWithNotNumbers() {
        String testedValue = "k";
        ByteArrayInputStream in = new ByteArrayInputStream(testedValue.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream inputStream = System.in;
        System.setIn(in);
        System.setOut(new PrintStream(out));
        assertThatThrownBy(() -> Calculator.getOperand())
                .isInstanceOf(IllegalStateException.class).describedAs("Ошибка в вводимых данных");
        System.setIn(inputStream);
        System.setOut(null);
    }

}
