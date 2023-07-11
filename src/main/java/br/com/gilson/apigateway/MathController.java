package br.com.gilson.apigateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

	@GetMapping("/operations/{num1}/{num2}/{operator}")
	public ResponseEntity<Double> operations(@PathVariable(value = "num1") String num1,
			@PathVariable(value = "num2") String num2, @PathVariable(value = "operator") String operator)
			throws Exception {

		if (!isNumeric(num1) || !isNumeric(num2)) {
			throw new UnsupportedOperationException("Please set a numeric value!");
		}

		var result = calculationOfOperations(num1, num2, operator);

		return ResponseEntity.ok(result);
	}

	private boolean isNumeric(String value) {
		if (value == null) {
			System.out.println("entrou");
			return false;
		}
		var number = value.replace(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

	private double calculationOfOperations(String num1, String num2, String operator) {

		switch (operator) {
		case "+": {
			return Double.parseDouble(num1) + Double.parseDouble(num2);
		}
		case "-": {
			return Double.parseDouble(num1) - Double.parseDouble(num2);
		}
		case "x": {
			return Double.parseDouble(num1) * Double.parseDouble(num2);
		}
		case "÷": {
			if (Double.parseDouble(num2) == 0) {
				throw new UnsupportedOperationException("Division by zero is not defined!");
			}
			return Double.parseDouble(num1) / Double.parseDouble(num2);
		}
		default:
			throw new UnsupportedOperationException("Unsupported operation! accepted operators['+', '-', 'x', '÷']");
		}

	}
}
