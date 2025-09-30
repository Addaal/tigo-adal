package com.adal.tigo.validator;

import com.adal.tigo.Exception.CreatureException;
import com.adal.tigo.Exception.ErrorCodes;
import com.adal.tigo.model.Creature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ValidateCreature {

    private static final Logger logger = LoggerFactory.getLogger(ValidateCreature.class);

    // Private constructor to prevent instantiation
    private ValidateCreature() {}

    public static void validate(Creature creature) throws CreatureException {
        if (creature == null) {
            logger.error("Creature cannot be null");
            throw new CreatureException(
                    java.time.Instant.now().toString(),
                    ErrorCodes.REQUEST,
                    "Creature cannot be null",
                    400
            );
        }

        if (creature.getName() == null || creature.getName().trim().isEmpty()) {
            logger.error("Name cannot be null/empty");
            throw new CreatureException(
                    java.time.Instant.now().toString(),
                    ErrorCodes.REQUEST,
                    "[name] cannot be empty",
                    400
            );
        }

        if (creature.getNotes() == null || creature.getNotes().trim().isEmpty()) {
            logger.error("Notes cannot be null/empty");
            throw new CreatureException(
                    java.time.Instant.now().toString(),
                    ErrorCodes.REQUEST,
                    "[notes] cannot be empty",
                    400
            );
        }

        if (creature.getActive() == null) {
            logger.error("Active flag cannot be null");
            throw new CreatureException(
                    java.time.Instant.now().toString(),
                    ErrorCodes.REQUEST,
                    "[active] cannot be null",
                    400
            );
        }
    }
}
