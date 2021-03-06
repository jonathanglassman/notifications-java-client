package uk.gov.service.notify;


import org.joda.time.DateTime;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class TemplateTest {

    @Test
    public void testTemplate_canCreateObjectFromJson() {
        JSONObject content = new JSONObject();
        String id = UUID.randomUUID().toString();
        content.put("id", id);
        content.put("type", "email");
        content.put("created_at", "2017-05-01T08:30:00.000Z");
        content.put("updated_at", "2017-05-01T08:34:00.000Z");
        content.put("version", 3);
        content.put("body", "The body of the template. For ((name)) eyes only.");
        content.put("subject", "Private email");

        Template template = new Template(content.toString());
        assertEquals(UUID.fromString(id), template.getId());
        assertEquals("email", template.getTemplateType());
        assertEquals(new DateTime("2017-05-01T08:30:00.000Z"), template.getCreatedAt());
        assertEquals(Optional.of(new DateTime("2017-05-01T08:34:00.000Z")), template.getUpdatedAt());
        assertEquals(3, template.getVersion());
        assertEquals("The body of the template. For ((name)) eyes only.", template.getBody());
        assertEquals(Optional.of("Private email"), template.getSubject());
    }



    @Test
    public void testTemplate_canCreateObjectFromJsonWithOptionals() {
        JSONObject content = new JSONObject();
        String id = UUID.randomUUID().toString();
        content.put("id", id);
        content.put("type", "email");
        content.put("created_at", "2017-05-01T08:30:00.000Z");
        content.put("updated_at", null);
        content.put("version", 3);
        content.put("body", "The body of the template. For ((name)) eyes only.");
        content.put("subject", null);

        Template template = new Template(content.toString());
        assertEquals(UUID.fromString(id), template.getId());
        assertEquals("email", template.getTemplateType());
        assertEquals(new DateTime("2017-05-01T08:30:00.000Z"), template.getCreatedAt());
        assertEquals(Optional.ofNullable(null), template.getUpdatedAt());
        assertEquals(3, template.getVersion());
        assertEquals("The body of the template. For ((name)) eyes only.", template.getBody());
        assertEquals(Optional.ofNullable(null), template.getSubject());
    }

}