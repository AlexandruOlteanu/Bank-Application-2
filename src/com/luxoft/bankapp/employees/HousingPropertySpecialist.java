package com.luxoft.bankapp.employees;


public class HousingPropertySpecialist extends AbstractSpecialist {

    @Override
    public boolean makeDecision(double amount, double creditRate, double clientSalary, boolean propertyGuarantee) {
        if (null != this.getNextSpecialist() && propertyGuarantee) {
            System.out.format("Approved at the %s level, passing to next specialist%n", this.getClass().getSimpleName());
            return this.getNextSpecialist().makeDecision(amount, creditRate, clientSalary, propertyGuarantee);
        }

        printFinalDecision(propertyGuarantee);

        return propertyGuarantee;
    }

}