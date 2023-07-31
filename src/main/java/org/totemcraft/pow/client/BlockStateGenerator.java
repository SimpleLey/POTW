package org.totemcraft.pow.client;

public interface BlockStateGenerator {
    static String of(String model) {
        return """
                {
                  "variants": {
                    "": {
                      "model": "%s"
                    }
                  }
                }
                """.formatted(model);
    }
}
