/*
 * Copyright (c) 2016. See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mbrlabs.mundus.scene3d.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.mbrlabs.mundus.commons.model.MModelInstance;
import com.mbrlabs.mundus.commons.scene3d.GameObject;
import com.mbrlabs.mundus.commons.scene3d.components.AbstractComponent;
import com.mbrlabs.mundus.core.Mundus;
import com.mbrlabs.mundus.shader.Shaders;
import com.sun.org.apache.regexp.internal.RE;

import java.util.Random;

/**
 * @author Marcus Brummer
 * @version 17-01-2016
 */
public class ModelComponent extends AbstractComponent {

    private MModelInstance modelInstance;
    private Shader shader;

    public ModelComponent(GameObject go) {
        super(go);
        type = Type.MODEL;
    }

    public Shader getShader() {
        return shader;
    }

    public void setShader(Shader shader) {
        this.shader = shader;
    }

    public void setModelInstance(MModelInstance modelInstance) {
        this.modelInstance = modelInstance;
    }

    public void encodeRaypickColorId() {
        this.modelInstance.modelInstance.materials.get(0).set(ColorAttribute.createDiffuse(
                new Color(new Color(new Random().nextInt()))
        ));
    }

    public MModelInstance getModelInstance() {
        return modelInstance;
    }

    @Override
    public void render(float delta) {
        if(Mundus.RAY_PICK_RENDERING) {
            gameObject.sceneGraph.batch.render(modelInstance.modelInstance, Shaders.raypickShader);
        } else {
            gameObject.sceneGraph.batch.render(modelInstance.modelInstance,
                    gameObject.sceneGraph.scene.environment, shader);
        }
    }

    @Override
    public void update(float delta) {

    }

}