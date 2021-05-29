package pack;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Main {

	public static void main(String[] args) {

		int tunaExcludingTax = 198;
		int eatIntaxRate = 10;
		int takeOutTaxRate = 8;


		// Integer型の値を２つ受け取り、Integer型の値（積）を返却する関数
		//BiFunction<T,T,R>
		BiFunction<Integer, Integer, Integer> calculation = (excludingTax, taxRate) -> excludingTax * taxRate;
		System.out.println(calculation.apply(tunaExcludingTax, eatIntaxRate));

		// Integer型の値を１つ受け取り、「Integer型の値を１つ受け取りInteger型の値を返却する関数」を返却する関数（calculationのカリー化）
		//Function<T,R>
		Function<Integer, Function<Integer, Integer>> curriedCalculation = excludingTax -> taxRate -> calculation.apply(excludingTax, taxRate);

		// 部分適用(イートイン)
		//Function<T,R>
		Function<Integer, Integer> curriedAmountEatEatIn = curriedCalculation.apply(eatIntaxRate);
		System.out.println(curriedAmountEatEatIn.apply(tunaExcludingTax));

		// 部分適用する(テイクアウト)
		//Function<T,R>
		Function<Integer, Integer> curriedAmountTakeOut = curriedCalculation.apply(takeOutTaxRate);
		System.out.println(curriedAmountTakeOut.apply(tunaExcludingTax));


		// Integer型の値を２つ受け取り、Integer型の値（積）を返却する関数
		System.out.println(calculationTaxIncluded(tunaExcludingTax, eatIntaxRate));
		// イートイン
		System.out.println(amountEatEatIn(tunaExcludingTax));
		// テイクアウト
		System.out.println(amountTakeOut(tunaExcludingTax));

	}

	public static int calculationTaxIncluded(int excludingTax, int taxRate) {
		return excludingTax*taxRate;
	}

	//イートイン
	public static int amountEatEatIn(int excludingTax) {
		int taxRate = 10;
		return calculationTaxIncluded(excludingTax,taxRate);
	}

	//テイクアウト
	public static int amountTakeOut(int excludingTax) {
		int taxRate = 8;
		return calculationTaxIncluded(excludingTax,taxRate);
	}

}

