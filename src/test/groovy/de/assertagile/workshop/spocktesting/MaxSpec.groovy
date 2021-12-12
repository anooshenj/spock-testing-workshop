package de.assertagile.workshop.spocktesting

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class MaxSpec extends Specification {
    def "maximum of #a and #b is #c"() {
        expect:
        Math.max(a, b) == c

        where:
        a | b | c
        3 | 5 | 3
        7 | 0 | 7
        0 | 0 | 0
    }
}