package com.bob.bobmobileapp.http.beans;

import java.util.ArrayList;
import java.util.List;


public class DesignResponse {
	
	private ApplicativeResponse statusResponse;
	
	private List<MnNode> nodes;
	
	private List<MnNodeProp> nodesProperties;
	
	private List<MnLeaf> bullets;
	
	private List<MnLeafProp> bulletsProperties;

	public DesignResponse() {
		this.statusResponse = new ApplicativeResponse();
	}
	
	
	public void addNode(MnNode node) {
		if(this.nodes == null) {
			this.nodes = new ArrayList<MnNode>();
		}
		this.nodes.add(node);
	}
	
	
	public void addNodeProp(MnNodeProp nodeProp) {
		if(this.nodesProperties == null) {
			this.nodesProperties = new ArrayList<MnNodeProp>();
		}
		this.nodesProperties.add(nodeProp);
	}
	
	
	public void addBullet(MnLeaf bullet) {
		if(this.bullets == null) {
			this.bullets = new ArrayList<MnLeaf>();
		}
		this.bullets.add(bullet);
	}
	
	
	public void addBulletProp(MnLeafProp node) {
		if(this.bulletsProperties == null) {
			this.bulletsProperties = new ArrayList<MnLeafProp>();
		}
		this.bulletsProperties.add(node);
	}


	public ApplicativeResponse getStatusResponse() {
		return statusResponse;
	}


	public void setStatusResponse(ApplicativeResponse statusResponse) {
		this.statusResponse = statusResponse;
	}


	public List<MnNode> getNodes() {
		return nodes;
	}


	public void setNodes(List<MnNode> nodes) {
		this.nodes = nodes;
	}


	public List<MnNodeProp> getNodesProperties() {
		return nodesProperties;
	}


	public void setNodesProperties(List<MnNodeProp> nodesProperties) {
		this.nodesProperties = nodesProperties;
	}


	public List<MnLeaf> getBullets() {
		return bullets;
	}


	public void setBullets(List<MnLeaf> bullets) {
		this.bullets = bullets;
	}


	public List<MnLeafProp> getBulletsProperties() {
		return bulletsProperties;
	}


	public void setBulletsProperties(List<MnLeafProp> bulletsProperties) {
		this.bulletsProperties = bulletsProperties;
	}
	
	
	
}
