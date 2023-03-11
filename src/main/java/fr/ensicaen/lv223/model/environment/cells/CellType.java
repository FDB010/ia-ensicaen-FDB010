package fr.ensicaen.lv223.model.environment.cells;

/**
 * The {@code CellType} enumeration represents the different types of cells
 * that can exist in the two-dimensional grid of LV-223.
 */
public enum CellType {
    BASE,           /**<! A cell that contains the base of the colony (is part of the exoskeleton). */
    // BOUNDARY,       /**<! NOT YET USED neither implemented in this model */
    DESERT,         /**<! A cell that contains a desert. */
    DRY_GRASS,      /**<! A cell that contains a dry meadow. */
    FOOD,           /**<! A cell that contains a some food. */
    FOREST,         /**<! A cell that contains a forest. */
    GRASS,          /**<! A cell that contains a meadow. */
    IMPENETRABLE,   /**<! A cell that is impenetrable (is part of the exoskeleton). */
    LAKE,           /**<! A cell that contains a lake (is part of the exoskeleton). */
    ORE,            /**<! A cell that contains mineral deposits (is part of the exoskeleton). */
    STONE,          /**<! A cell that contains stones (is part of the exoskeleton). */
    WET_GRASS,      /**<! A cell that contains a wet meadow. */
    UNKNOWN;        /**<! A cell that is unknown. */

    public static boolean isExtractableDirectly(CellType type) {
        return type == ORE || type == FOOD;
    }

}
