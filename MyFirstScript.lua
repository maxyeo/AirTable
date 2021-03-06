scriptId = 'com.thalmic.examples.myfirstscript'
scriptTitle = "My First Script"
scriptDetailsUrl = ""

prevPitch = myo.getPitch()
prevRoll = 0.9
prevYaw = -1
--prevRoll = myo.getRoll()
--prevYaw = myo.getYaw()
initialRoll = myo.getRoll()

function onForegroundWindowChange(app, title)
	myo.debug("onForegroundWindowChange: " .. app .. ", " .. title)
	local titleMatch = string.match(title, "AirTable") ~= nil;
    myo.debug("Music: "  .. tostring(titleMatch))
    if (titleMatch) then
        myo.setLockingPolicy("none")
    end
    
    return titleMatch
	--appTitle = yo
	--return true
end


function onPoseEdge(pose, edge)
    myo.debug("onPoseEdge: " .. pose .. ": " .. edge)
    
    pose = conditionallySwapWave(pose)
    
    local keyEdge = edge == "off" and "up" or "down"
    
    if (pose ~= "rest" and pose ~= "unknown") then
        -- hold if edge is on, timed if edge is off
		if (edge == "off") then
			myo.unlock("timed")
		else 
			myo.unlock("hold")
		end
        --myo.unlock(edge == "off" and "timed" or "hold")
	
end

	currentRoll = myo.getRoll()

    if (pose == "waveOut") then
	    myo.unlock("hold")
        onWaveOut(keyEdge)        
    elseif (pose == "waveIn") then
        onWaveIn(keyEdge)
    elseif (pose == "fist") then
		myo.unlock("timed")
        onFist(keyEdge)
    elseif (pose == "fingersSpread") then
        onFingersSpread(keyEdge)
	elseif (pose == "doubleTap") then
		onDoubleTap(keyEdge)
    end

	if (pose == "waveIn" and math.abs(currentRoll - initialRoll) < 0.8) then
		myo.keyboard("s", "press")
		myo.vibrate("short")
        myo.debug("~~~~~~~~~~~~~drop the bass~~~~~~~~~~~")
    end
	
end

function activeAppName()
	return appTitle
end

function onPeriodic() 
	currentPitch = myo.getPitch()
	currentRoll = myo.getRoll()
	currentYaw = myo.getYaw()
	
	--myo.debug("current pitch: " .. currentPitch)
	--myo.debug("current roll: " .. currentRoll)
	--myo.debug("current yaw: " .. currentYaw)
	
	local deltaPitch = currentPitch - prevPitch
	local deltaYaw = currentYaw - prevYaw
	local deltaRoll = currentRoll - prevRoll 
	
	if (deltaPitch > 0.108 and deltaPitch < 0.216) then
		myo.keyboard("up_arrow", "press")
		prevPitch = currentPitch
		myo.debug("***********press one up button ******************")
	elseif (deltaPitch > -0.216 and deltaPitch < -0.108) then
		myo.keyboard("down_arrow", "press")
		prevPitch = currentPitch
		myo.debug("*****press one DOWN button *****")
	end
	
	if (deltaYaw > 0.16 and deltaYaw < 0.32) then
		myo.keyboard("right_arrow", "press")
		prevYaw = currentYaw
		myo.debug("press right")
	elseif (deltaYaw > -0.32 and deltaYaw < -0.16) then
		myo.keyboard("left_arrow", "press")
		prevYaw = currentYaw
		myo.debug("press left")
	end
	
	if (deltaRoll > 0.3) then	
		myo.keyboard("f", "press")
		prevRoll = currentRoll
        myo.debug("roll right!!!!!!!!!!")
	elseif (deltaRoll < -0.3) then
		myo.keyboard("d", "press")
		prevRoll = currentRoll
        myo.debug("roll lefttttttt")
	end
	
end


function onWaveOut(keyEdge)	
    myo.debug("Next")
    --myo.vibrate("short")
    myo.keyboard("2", keyEdge)
end
 
function onWaveIn(keyEdge)
    myo.debug("Previous")
    --myo.vibrate("short")
    myo.keyboard("1",keyEdge)
end
 
 
function onFist(keyEdge)
    myo.debug("Mute")  
    --myo.vibrate("medium")
    myo.keyboard("space",keyEdge)
end
 
function onFingersSpread(keyEdge)
    myo.debug("Escape")
	--myo.vibrate("long")
    myo.keyboard("h", keyEdge)
end

function onDoubleTap(keyEdge)
	myo.debug("start")
	--myo.vibrate("long")
    myo.keyboard("p", keyEdge)
end


function conditionallySwapWave(pose)
	if myo.getArm() == "left" then
        if pose == "waveIn" then
            pose = "waveOut"
        elseif pose == "waveOut" then
            pose = "waveIn"
        end
    end
    return pose
end