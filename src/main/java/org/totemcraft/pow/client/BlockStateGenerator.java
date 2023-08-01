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

    static String ofFacing(String model) {
        return """
{
   "variants": {
     "facing=east": {
       "model": "%s",
       "y": 180
     },
     "facing=north": {
       "model": "%s",
       "y": 90
     },
     "facing=south": {
       "model": "%s",
       "y": 270
     },
     "facing=west": {
       "model": "%s",
       "y": 0
     }
   }
}
                """.formatted(model, model, model, model);
    }

    static String ofLowerOnly(String model) {
        return """
{
  "variants": {
    "half=lower": {
      "model": "%s"
    },
    "half=upper": {
      "model": "pow:empty"
    }
  }
}
                """.formatted(model);
    }
}
