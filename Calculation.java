package pack;

import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Calculation {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
	    int inputExcludingTax = scanner.nextInt();
	    int inputTaxRate = scanner.nextInt();
	    scanner.close();

	    /*
	     * 引数：税抜き価格、税率
	     */
	    // 関数型インターフェース
		BiFunction<Integer, Integer, Integer> calculation = (excludingTax, taxRate) -> excludingTax * taxRate;
		System.out.println(calculation.apply(inputExcludingTax, inputTaxRate));
		// 関数
		System.out.println(calculationTaxIncluded(inputExcludingTax, inputTaxRate));

		// カリー化
		Function<Integer, Function<Integer, Integer>> curriedCalculation = excludingTax -> taxRate -> calculation.apply(excludingTax, taxRate);
		// クラス
		AmountClass amountClass = new AmountClass(inputTaxRate);

	    /*
	     * 引数：税抜き価格
	     */
		// 関数型インターフェース
		Function<Integer, Integer> curriedAmount = curriedCalculation.apply(inputTaxRate);
		System.out.println(curriedAmount.apply(inputExcludingTax));
		// 関数
		System.out.println(amountClass.amount(inputExcludingTax));

	}

	public static int calculationTaxIncluded(int excludingTax, int inputTaxRate) {
		return excludingTax*inputTaxRate;
	}

}

class AmountClass {

	static int taxRate;

	AmountClass(int inputTaxRate){
		taxRate = inputTaxRate;
	}

	public int calculationTaxIncluded(int excludingTax, int inputTaxRate) {
		return excludingTax*inputTaxRate;
	}

	public int amount(int excludingTax) {
		return calculationTaxIncluded(excludingTax,taxRate);
	}

}

