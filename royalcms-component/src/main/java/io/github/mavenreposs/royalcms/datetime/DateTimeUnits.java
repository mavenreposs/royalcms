package io.github.mavenreposs.royalcms.datetime;

/**
 * DateTimeUnits
 * <p>
 * Define units used by {@link DateTimeUtils#getDateDiff(Date, Date, DateTimeUnits)}
 * and also {@link DateTimeUtils#formatDate(long, DateTimeUnits)}
 */
public enum DateTimeUnits {
    /**
     * Days
     */
    DAYS,
    /**
     * Hours
     */
    HOURS,
    /**
     * Minutes
     */
    MINUTES,
    /**
     * Seconds
     */
    SECONDS,
    /**
     * Milliseconds
     */
    MILLISECONDS,
}
