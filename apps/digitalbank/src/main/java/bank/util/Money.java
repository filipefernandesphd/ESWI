package bank.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class Money {
    private static final DecimalFormat FORMATTER;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.forLanguageTag("pt-BR"));
        FORMATTER = new DecimalFormat("#,##0.00", symbols);
        FORMATTER.setRoundingMode(RoundingMode.HALF_UP);
    }

    private Money() {
    }

    public static BigDecimal parse(String value) {
        String normalized = value.trim().replace("R$", "").replace(" ", "");
        if (normalized.contains(",")) {
            normalized = normalized.replace(".", "").replace(",", ".");
        }
        return normalize(new BigDecimal(normalized));
    }

    public static BigDecimal normalize(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }

    public static void requirePositive(BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }
    }

    public static String format(BigDecimal value) {
        return "R$ " + FORMATTER.format(normalize(value));
    }
}
