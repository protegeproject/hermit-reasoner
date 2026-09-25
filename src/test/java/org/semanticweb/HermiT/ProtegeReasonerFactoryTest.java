package org.semanticweb.HermiT;

import java.lang.reflect.Proxy;

import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.model.inference.ReasonerPreferences;
import org.protege.editor.owl.model.inference.ReasonerPreferences.OptionalInferenceTask;
import org.semanticweb.owlapi.reasoner.BufferingMode;
import org.semanticweb.owlapi.reasoner.NullReasonerProgressMonitor;

@SuppressWarnings("javadoc")
public class ProtegeReasonerFactoryTest extends AbstractHermiTTest {
    public void testReasonerFactory() {
        ProtegeReasonerFactory factory=new ProtegeReasonerFactory();

        assertEquals(BufferingMode.BUFFERING,factory.getRecommendedBuffering());
        assertTrue(factory.getReasonerFactory() instanceof ReasonerFactory);
    }

    public void testConfigurationUsesProtegeReasonerPreferences() {
        ReasonerPreferences preferences=new ReasonerPreferences();
        preferences.setEnabled(OptionalInferenceTask.SHOW_INFERRED_SUPER_CLASSES,true);
        preferences.setEnabled(OptionalInferenceTask.SHOW_INFERRED_SUPER_OBJECT_PROPERTIES,true);
        preferences.setEnabled(OptionalInferenceTask.SHOW_INFERRED_SUPER_DATATYPE_PROPERTIES,true);
        preferences.setEnabled(OptionalInferenceTask.SHOW_INFERRED_TYPES,true);
        preferences.setEnabled(OptionalInferenceTask.SHOW_INFERRED_OBJECT_PROPERTY_ASSERTIONS,true);
        preferences.setEnabled(OptionalInferenceTask.SHOW_INFERRED_DATA_PROPERTY_ASSERTIONS,true);
        preferences.setEnabled(OptionalInferenceTask.SHOW_INFERRED_OBJECT_PROPERTY_DOMAINS,true);
        preferences.setEnabled(OptionalInferenceTask.SHOW_INFERRED_OBJECT_PROPERTY_RANGES,true);

        OWLModelManager modelManager=(OWLModelManager)Proxy.newProxyInstance(
            OWLModelManager.class.getClassLoader(),
            new Class<?>[] { OWLModelManager.class },
            (proxy,method,args) -> method.getName().equals("getReasonerPreferences") ? preferences : null);

        ProtegeReasonerFactory factory=new ProtegeReasonerFactory();
        factory.setOWLModelManager(modelManager);
        Configuration configuration=(Configuration)factory.getConfiguration(new NullReasonerProgressMonitor());
        Configuration.PrepareReasonerInferences inferences=configuration.prepareReasonerInferences;

        assertTrue(configuration.ignoreUnsupportedDatatypes);
        assertTrue(inferences.classClassificationRequired);
        assertTrue(inferences.objectPropertyClassificationRequired);
        assertTrue(inferences.dataPropertyClassificationRequired);
        assertTrue(inferences.realisationRequired);
        assertTrue(inferences.objectPropertyRealisationRequired);
        assertTrue(inferences.dataPropertyRealisationRequired);
        assertTrue(inferences.objectPropertyDomainsRequired);
        assertTrue(inferences.objectPropertyRangesRequired);
        assertTrue(inferences.sameAs);
    }
}
