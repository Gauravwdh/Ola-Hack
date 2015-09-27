package com.travelgeeks.olahackathon.utilities;

import java.util.List;

class SmsUtil {

    /**
     * For XX-YYYY
     */
    private static final String PATTERN1 = "(?i)[A-Z]{2}-?[!,A-Z,0-9]{3,8}\\s*";

    /**
     * Any 3 digit number
     */
    private static final String PATTERN2 = "(?i)[0-9]{3}\\s*";


    static boolean isCompanyMessage(String sender) {
        if (sender == null || sender.length() == 0) {
            return false;
        }
        return sender.matches(PATTERN1) || sender.matches(PATTERN2);
    }

    /**
     * Check weather the message contains any one key provided.
     *
     * @param message
     * @param keys
     * @return
     */
    static boolean contain(String message, List<String> keys) {
        if (keys == null || keys.size() == 0 || message == null) {
            return false;
        }
        message = message.toLowerCase();
        for (String str : keys) {
            // added spaces both the side so that a string will be matched.
            if (message.contains(" " + str + " ")) return true;
        }
        return false;
    }


    /**
     * Return Short name for the sender like TD-AXIS then o/p is AXIS.
     *
     * @param sender
     * @return
     */
    static String getShortNameForSender(String sender) {
        String[] split = sender.split("-");
        if (split.length == 2) {
            return split[1];
        }
        if (split.length == 1) {
            if (sender.matches(PATTERN2)) {
                return split[0];
            }
            return sender.substring(2);
        }
        // No info about company name parsing so return null.
        return null;
    }
}
