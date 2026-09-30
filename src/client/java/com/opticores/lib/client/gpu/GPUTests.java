package com.opticores.lib.client.gpu;
import java.util.function.Supplier;
import static com.opticores.lib.client.gpu.GPUCapabilityManager.*;

public class GPUTests {
    public static void main(String[] args) {
        // Test basic detection
        assert getFeatureState("test_feature", () -> true) == State.SUPPORTED;
        assert getFeatureState("missing_feature", () -> false) == State.UNAVAILABLE;

        // Test failure state
        assert getFeatureState("fail_feature", () -> { throw new RuntimeException("boom"); }) == State.FAILED;

        // Test override
        setDisabled("test_feature");
        assert getFeatureState("test_feature", () -> true) == State.DISABLED;

        System.out.println("GPU capability tests passed.");
    }
}
