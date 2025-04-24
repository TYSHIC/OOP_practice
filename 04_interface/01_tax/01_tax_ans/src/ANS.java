// 任務描述：
// 根據使用者輸入的身分（employee、owner、freelancer）、地區（tw、us、jp）和收入（範圍為 [0, 2^15]），
// 計算並輸出應繳納的所得稅金額。
// 稅額的計算方式會根據角色和地區的不同而有所差異，具體規則如下：
//
// 不同身分稅務計算方式：
// - employee (勞工): 稅額為收入的 10%。
// - owner (企業家): 若收入低於 10000，稅額為 0；否則，稅額為超過 10000 部分的 30%。
// - freelancer (自由工作者): 稅額為收入的 15% 加上固定的 500。
//
// 不同地區稅務計算方式（在不同身分稅額的基礎上額外計算）：
// - tw (台灣): 額外加上收入的 5%。
// - us (美國): 額外加上固定的 100。
// - jp (日本): 額外加上收入的 2%。
//
// 輸入說明：
// 輸入共三行。
// 第一行：一個字串，表示使用者的身分，可能為 "employee"、"owner"、"freelancer"。
// 第二行：一個字串，表示使用者所在的地區，可能為 "tw"、"us"、"jp"。
// 第三行：一個整數，表示使用者的收入。
//
// 輸出說明：
// 輸出一個整數，表示根據輸入的角色、地區和收入計算出的所得稅金額（四捨五入到整數）。

import java.util.Scanner;

public class ANS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String role = scanner.nextLine();
        String region = scanner.nextLine();
        int income = scanner.nextInt();

        TaxStrategy identityStrategy = null;

        if (role.equals("employee"))
            identityStrategy = new EmployeeTaxStrategy();
        else if (role.equals("owner"))
            identityStrategy = new BusinessOwnerTaxStrategy();
        else if (role.equals("freelancer"))
            identityStrategy = new FreelancerTaxStrategy();

        RegionTaxStrategy regionStrategy = null;

        if (region.equals("tw"))
            regionStrategy = new TaiwanRegionTax();
        else if (region.equals("us"))
            regionStrategy = new USRegionTax();
        else if (region.equals("jp"))
            regionStrategy = new JapanRegionTax();

        TaxCalculator calculator = new TaxCalculator(identityStrategy, regionStrategy);
        System.out.println(Math.round(calculator.calculate(income)));
    }
}

interface TaxStrategy {
    // TODO: calculateTax(int income)
    double calculateTax(int income);
}

class EmployeeTaxStrategy implements TaxStrategy {
    @Override
    public double calculateTax(int income) {
        return income * 0.10;
    }
}

class BusinessOwnerTaxStrategy implements TaxStrategy {
    @Override
    public double calculateTax(int income) {
        if (income <= 10000) return 0;
        return (income - 10000) * 0.30;
    }
}

class FreelancerTaxStrategy implements TaxStrategy {
    @Override
    public double calculateTax(int income) {
        return income * 0.15 + 500;
    }
}

interface RegionTaxStrategy {
    // TODO: applyRegionalTax(double baseTax, int income)
    double applyRegionalTax(double baseTax, int income);
}

class TaiwanRegionTax implements RegionTaxStrategy {
    @Override
    public double applyRegionalTax(double baseTax, int income) {
        return baseTax + income * 0.05;
    }
}

class USRegionTax implements RegionTaxStrategy {
    @Override
    public double applyRegionalTax(double baseTax, int income) {
        return baseTax + 100;
    }
}

class JapanRegionTax implements RegionTaxStrategy {
    @Override
    public double applyRegionalTax(double baseTax, int income) {
        return baseTax + income * 0.02;
    }
}


class TaxCalculator {
    private final TaxStrategy identityStrategy;
    private final RegionTaxStrategy regionStrategy;

    public TaxCalculator(TaxStrategy identityStrategy, RegionTaxStrategy regionStrategy) {
        this.identityStrategy = identityStrategy;
        this.regionStrategy = regionStrategy;
    }

    public double calculate(int income) {
        double baseTax = identityStrategy.calculateTax(income);
        return regionStrategy.applyRegionalTax(baseTax, income);
    }
}
