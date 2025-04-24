package jpk.cqrs.config.constants;

import lombok.Getter;

/**
 * Enum class for constant values.
 */
@Getter
public enum AppConstants {

    MODEL_PACKAGE_LOCATION("jpk.cqrs.model");

    private final String value;

    AppConstants(String value) {
        this.value = value;
    }
}
