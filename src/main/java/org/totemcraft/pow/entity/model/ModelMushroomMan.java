package org.totemcraft.pow.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import org.totemcraft.pow.R;

public class ModelMushroomMan<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(R.location("mushroom_man"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public ModelMushroomMan(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.leftArm = root.getChild("leftArm");
		this.rightArm = root.getChild("rightArm");
		this.leftLeg = root.getChild("leftLeg");
		this.rightLeg = root.getChild("rightLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(40, 41).addBox(-4.0F, -27.0F, -2.0F, 8.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 41).addBox(-5.0F, 2.0F, -5.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-10.0F, -2.0F, -10.0F, 20.0F, 4.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(0, 24).addBox(-7.0F, -5.0F, -7.0F, 14.0F, 3.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -38.0F, 0.0F));

		body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(12, 0).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -27.0F, 0.0F));
		body.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(38, 59).addBox(0.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -27.0F, 0.0F));
		body.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 0).addBox(-3.8F, -1.0F, -1.5F, 3.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));
		body.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(56, 24).addBox(0.8F, -1.0F, -1.5F, 3.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}