///*
// * JGrass - Free Open Source Java GIS http://www.jgrass.org 
// * (C) HydroloGIS - www.hydrologis.com 
// * 
// * This library is free software; you can redistribute it and/or modify it under
// * the terms of the GNU Library General Public License as published by the Free
// * Software Foundation; either version 2 of the License, or (at your option) any
// * later version.
// * 
// * This library is distributed in the hope that it will be useful, but WITHOUT
// * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
// * FOR A PARTICULAR PURPOSE. See the GNU Library General Public License for more
// * details.
// * 
// * You should have received a copy of the GNU Library General Public License
// * along with this library; if not, write to the Free Foundation, Inc., 59
// * Temple Place, Suite 330, Boston, MA 02111-1307 USA
// */
//package eu.udig.omsbox.view.widgets;
//
//import java.util.List;
//
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Control;
//import org.geotools.data.simple.SimpleFeatureCollection;
//
//import eu.udig.omsbox.core.FieldData;
//import eu.udig.omsbox.core.ModuleDescription;
//import eu.udig.omsbox.core.OmsModulesManager;
//
///**
// * Class representing an feature input selector gui.
// * 
// * @author Andrea Antonello (www.hydrologis.com)
// */
//public class GuiFeatureInputField extends ModuleGuiElement {
//
//    private final String constraints;
//
//    private FieldData data;
//
//    private static ModuleDescription selectedVectorReader;
//
//    public GuiFeatureInputField( FieldData data, String constraints ) {
//        this.data = data;
//        this.constraints = constraints;
//    }
//
//    @Override
//    public Control makeGui( Composite parent ) {
//        Composite composite = new Composite(parent, SWT.NONE);
//        composite.setLayoutData(constraints);
//        GridLayout layout = new GridLayout(1, false);
//        layout.marginWidth = 0;
//        layout.marginHeight = 0;
//        composite.setLayout(layout);
//
//        final Button browseButton = new Button(composite, SWT.PUSH);
//        browseButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
//        browseButton.setText("...");
//        browseButton.addSelectionListener(new SelectionAdapter(){
//
//            public void widgetSelected( SelectionEvent e ) {
//                List<ModuleDescription> vectorReaders = OmsModulesManager.getInstance().getFeatureReaders();
//                MultipleModuleDescriptionDialog dialog = new MultipleModuleDescriptionDialog("Choose Input Vector Reader",
//                        vectorReaders);
//                if (selectedVectorReader != null) {
//                    dialog.setLastUsedModuleDescription(selectedVectorReader);
//                }
//                dialog.open(browseButton.getShell());
//                selectedVectorReader = dialog.getModuleDescription();
//
//                // find the field, assuming that IO modules can have only one connecting type.
//                List<FieldData> outputList = selectedVectorReader.getOutputsList();
//                for( FieldData fieldData : outputList ) {
//                    if (fieldData.fieldType.equals(SimpleFeatureCollection.class.getCanonicalName())) {
//                        data.otherFieldName = fieldData.fieldName;
//                        data.otherModule = selectedVectorReader;
//                        break;
//                    }
//                }
//            }
//        });
//        return null;
//    }
//
//    public FieldData getFieldData() {
//        return data;
//    }
//
//    public boolean hasData() {
//        return true;
//    }
//
//    @Override
//    public String validateContent() {
//        return null;
//    }
//
//}
