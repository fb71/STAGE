/*
 * Stage - Spatial Toolbox And Geoscript Environment 
 * (C) HydroloGIS - www.hydrologis.com 
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * (http://www.eclipse.org/legal/epl-v10.html).
 */
package eu.hydrologis.rap.stage.widgets;

import java.util.ArrayList;
import java.util.List;

import eu.hydrologis.rap.stage.core.FieldData;
import eu.hydrologis.rap.stage.utils.StageConstants;

/**
 * A factory for guis.
 * 
 * @author Andrea Antonello (www.hydrologis.com)
 */
@SuppressWarnings("nls")
public class ModuleGuiFactory {

    public static int COLUMNS = 12;

    /**
     * Create the gui for the input field.
     * 
     * @param inputData the input data.
     * @param row the row of this item, used for the constraints.
     * @return the gui for the form element.
     */
    public List<ModuleGuiElement> createInputGui( FieldData inputData, int[] row ) {

        List<ModuleGuiElement> guiElements = new ArrayList<ModuleGuiElement>();
        if (inputData != null)
            if (isAtLeastOneAssignable(inputData.fieldType, String.class)) {
                if (inputData.guiHints != null && inputData.guiHints.startsWith(StageConstants.MULTILINE_UI_HINT)) {
                    handleTextArea(inputData, row, guiElements);
                } else if (inputData.guiHints != null && inputData.guiHints.startsWith(StageConstants.COMBO_UI_HINT)) {
                    handleComboField(inputData, row, guiElements);
                } else {
                    handleTextField(inputData, row, guiElements);
                }
            } else if (isAtLeastOneAssignable(inputData.fieldType, Double.class, double.class)) {
                handleTextField(inputData, row, guiElements);
            } else if (isAtLeastOneAssignable(inputData.fieldType, Float.class, float.class)) {
                handleTextField(inputData, row, guiElements);
            } else if (isAtLeastOneAssignable(inputData.fieldType, Integer.class, int.class)) {
                handleTextField(inputData, row, guiElements);
            } else if (isAtLeastOneAssignable(inputData.fieldType, Short.class, short.class)) {
                handleTextField(inputData, row, guiElements);
            } else if (isAtLeastOneAssignable(inputData.fieldType, Boolean.class, boolean.class)) {
                handleBooleanField(inputData, row, guiElements);
                // } else if (isAtLeastOneAssignable(inputData.fieldType, GridCoverage2D.class)) {
                // handleGridcoverageInputField(inputData, row, guiElements);
                // } else if (isAtLeastOneAssignable(inputData.fieldType, GridGeometry2D.class)) {
                // handleGridgeometryInputField(inputData, row, guiElements);
                // } else if (isAtLeastOneAssignable(inputData.fieldType,
                // SimpleFeatureCollection.class)) {
                // handleFeatureInputField(inputData, row, guiElements);
                // } else if (isAtLeastOneAssignable(inputData.fieldType, HashMap.class)) {
                // handleHashMapInputField(inputData, row, guiElements);
                // } else if (isAtLeastOneAssignable(inputData.fieldType, List.class)) {
                // if (inputData.guiHints != null &&
                // inputData.guiHints.equals(OmsBoxConstants.FILESPATHLIST_UI_HINT)) {
                // handleFilesPathListInputField(inputData, row, guiElements);
                // } else {
                // handleListInputField(inputData, row, guiElements);
                // }
            } else {
                if (!inputData.fieldType.endsWith("ProgressMonitor")) {
                    System.out.println("Skipping input field: " + inputData.fieldType);
                }
            }

        return guiElements;
        // throw new IllegalArgumentException();
    }
    /**
     * Create the gui for the output field.
     * 
     * @param outputData the output data.
     * @param row the row of this item, used for the constraints.
     * @return the gui for the form element.
     */
    public List<ModuleGuiElement> createOutputGui( FieldData outputData, int[] row ) {

        List<ModuleGuiElement> guiElements = new ArrayList<ModuleGuiElement>();
        // if (isAtLeastOneAssignable(outputData.fieldType, String.class)) {
        // handleTextField(outputData, row, guiElements);
        // } else if (isAtLeastOneAssignable(outputData.fieldType, Double.class, double.class)) {
        // handleTextField(outputData, row, guiElements);
        // } else if (isAtLeastOneAssignable(outputData.fieldType, Integer.class, int.class)) {
        // handleTextField(outputData, row, guiElements);
        // } else if (isAtLeastOneAssignable(outputData.fieldType, Boolean.class, boolean.class)) {
        // handleBooleanField(outputData, row, guiElements);
        // } else
        // if (isAtLeastOneAssignable(outputData.fieldType, GridCoverage2D.class)) {
        // handleGridcoverageOutputField(outputData, row, guiElements);
        // } else if (isAtLeastOneAssignable(outputData.fieldType, SimpleFeatureCollection.class)) {
        // handleFeatureOutputField(outputData, row, guiElements);
        // } else
        // if (isAtLeastOneAssignable(outputData.fieldType, HashMap.class)) {
        // handleHashMapOutputField(outputData, row, guiElements);
        // } else if (isAtLeastOneAssignable(outputData.fieldType, List.class)) {
        // handleListOutputField(outputData, row, guiElements);
        // } else {
        // if (outputData != null)
        // System.out.println("Skipping output field: " + outputData.fieldType);
        // }
        return guiElements;
        // throw new IllegalArgumentException();
    }

    private String extractSingleGuiHint( String pattern, String guiHints ) {
        String[] split = guiHints.split(",");
        for( String hint : split ) {
            hint = hint.trim();
            if (hint.contains(pattern)) {
                return hint;
            }
        }
        return null;
    }

    /**
     * Checks if one class is assignable from at least one of the others.
     * 
     * @param main the chanonical name of class to check.
     * @param classes the other classes.
     * @return true if at least one of the other classes match.
     */
    private boolean isAtLeastOneAssignable( String main, Class< ? >... classes ) {
        for( Class< ? > clazz : classes ) {
            if (clazz.getCanonicalName().equals(main)) {
                return true;
            }
        }
        return false;
    }

    private void handleTextField( FieldData data, int[] row, List<ModuleGuiElement> guiElements ) {
        StringBuilder sb = new StringBuilder();
        sb.append("cell ");
        sb.append(0);
        sb.append(" ");
        sb.append(row[0]);
        sb.append(" ");
        int labelCol = COLUMNS / 5;
        sb.append(labelCol);
        sb.append(" ");
        sb.append(1);
        // sb.append(", growx");
        String labelConstraint = sb.toString();

        boolean isBold = false;
        String guiHints = data.guiHints;
        if (guiHints != null) {
            if (guiHints.contains(StageConstants.FILEOUT_UI_HINT) || guiHints.contains(StageConstants.FOLDEROUT_UI_HINT)) {
                isBold = true;
            }
        }
        GuiLabel label = new GuiLabel(data, labelConstraint, isBold);
        guiElements.add(label);

        sb = new StringBuilder();
        sb.append("cell ");
        sb.append(labelCol + 1);
        sb.append(" ");
        sb.append(row[0]);
        sb.append(" ");
        sb.append(COLUMNS - labelCol);
        sb.append(" ");
        sb.append(1);
        sb.append(", growx");
        String textConstraint = sb.toString();

        GuiTextField text = new GuiTextField(data, textConstraint);
        guiElements.add(text);
    }

    private void handleTextArea( FieldData data, int[] row, List<ModuleGuiElement> guiElements ) {
        StringBuilder sb = new StringBuilder();
        sb.append("cell ");
        sb.append(0);
        sb.append(" ");
        sb.append(row[0]);
        sb.append(" ");
        sb.append(COLUMNS);
        sb.append(" ");
        sb.append(1);
        sb.append(", growx");
        // sb.append(", growx");
        String labelConstraint = sb.toString();

        GuiLabel label = new GuiLabel(data, labelConstraint, false);
        guiElements.add(label);

        String hint = extractSingleGuiHint(StageConstants.MULTILINE_UI_HINT, data.guiHints);
        String rowsStr = hint.replaceFirst(StageConstants.MULTILINE_UI_HINT, "");
        row[0] = row[0] + 1;
        sb = new StringBuilder();
        sb.append("cell ");
        sb.append(0);
        sb.append(" ");
        sb.append(row[0]);
        sb.append(" ");
        sb.append(COLUMNS);
        sb.append(" ");
        sb.append(rowsStr);
        row[0] = row[0] + Integer.parseInt(rowsStr);
        sb.append(", growx");
        String textConstraint = sb.toString();

        GuiTextField text = new GuiTextField(data, textConstraint);
        guiElements.add(text);
    }

    private void handleBooleanField( FieldData data, int[] row, List<ModuleGuiElement> guiElements ) {
        StringBuilder sb = new StringBuilder();
        sb.append("cell ");
        sb.append(0);
        sb.append(" ");
        sb.append(row[0]);
        sb.append(" ");
        int labelCol = COLUMNS / 3;
        sb.append(labelCol);
        sb.append(" ");
        sb.append(1);
        // sb.append(", growx");
        String labelConstraint = sb.toString();

        GuiLabel label = new GuiLabel(data, labelConstraint, false);
        guiElements.add(label);

        sb = new StringBuilder();
        sb.append("cell ");
        sb.append(labelCol + 1);
        sb.append(" ");
        sb.append(row[0]);
        sb.append(" ");
        sb.append(COLUMNS - labelCol);
        sb.append(" ");
        sb.append(1);
        sb.append(", growx");
        String textConstraint = sb.toString();

        GuiBooleanField booleanField = new GuiBooleanField(data, textConstraint);
        guiElements.add(booleanField);
    }

    private void handleComboField( FieldData data, int[] row, List<ModuleGuiElement> guiElements ) {
        StringBuilder sb = new StringBuilder();
        sb.append("cell ");
        sb.append(0);
        sb.append(" ");
        sb.append(row[0]);
        sb.append(" ");
        int labelCol = COLUMNS / 5;
        sb.append(labelCol);
        sb.append(" ");
        sb.append(1);
        // sb.append(", growx");
        String labelConstraint = sb.toString();

        GuiLabel label = new GuiLabel(data, labelConstraint, false);
        guiElements.add(label);

        sb = new StringBuilder();
        sb.append("cell ");
        sb.append(labelCol + 1);
        sb.append(" ");
        sb.append(row[0]);
        sb.append(" ");
        sb.append(COLUMNS - labelCol);
        sb.append(" ");
        sb.append(1);
        sb.append(", growx");
        String textConstraint = sb.toString();

        GuiComboField text = new GuiComboField(data, textConstraint);
        guiElements.add(text);
    }
}
