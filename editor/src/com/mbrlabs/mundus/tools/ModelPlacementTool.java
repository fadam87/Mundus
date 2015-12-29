/*
 * Copyright (c) 2015. See AUTHORS file.
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

package com.mbrlabs.mundus.tools;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mbrlabs.mundus.core.project.ProjectContext;
import com.mbrlabs.mundus.model.MModel;
import com.mbrlabs.mundus.model.MModelInstance;

/**
 * @author Marcus Brummer
 * @version 25-12-2015
 */
public class ModelPlacementTool extends Tool {

    public static final String NAME = "Placement Tool";

    private Vector3 tempV3 = new Vector3();

    // DO NOT DISPOSE THIS
    private MModel model;
    private MModelInstance curEntity;

    public ModelPlacementTool(ProjectContext projectContext, Shader shader, ModelBatch batch) {
        super(projectContext, shader, batch);
        model = null;
        curEntity = null;
    }

    public void setModel(MModel model) {
        this.model = model;
        curEntity = new MModelInstance(model);
        curEntity.calculateBounds();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Drawable getIcon() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void render() {
        if(curEntity != null) {
            batch.begin(projectContext.currScene.cam);
            batch.render(curEntity.modelInstance, projectContext.currScene.environment, shader);
            batch.end();
        }
    }

    @Override
    public void act() {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(curEntity != null && button == Input.Buttons.LEFT) {
            projectContext.currScene.entities.add(curEntity);
            curEntity = new MModelInstance(model);
            curEntity.calculateBounds();
            mouseMoved(screenX, screenY);
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Ray ray = projectContext.currScene.cam.getPickRay(screenX, screenY);
        if(projectContext.currScene.terrainGroup.size() > 0 && curEntity != null) {
            projectContext.currScene.terrainGroup.getRayIntersection(tempV3, ray);
        } else {
            tempV3.set(projectContext.currScene.cam.position);
            tempV3.add(ray.direction.nor().scl(200));
        }
        curEntity.modelInstance.transform.setTranslation(tempV3);
        return false;
    }

    @Override
    public void dispose() {

    }

}