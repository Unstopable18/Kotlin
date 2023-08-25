package com.example.a7minworkout

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel> {

        val exerciseList = ArrayList<ExerciseModel>()

        val shoulder_stretch =
            ExerciseModel(
                1, "Shoulder Stretch", "shoulder_stretch.json",
                isCompleted = false,
                isSelected = false
            )
        exerciseList.add(shoulder_stretch)
        val single_leg_hip_down =
            ExerciseModel(
                1, "Single Leg Hip Down", "single_leg_hip_down.json",
                isCompleted = false,
                isSelected = false
            )
        exerciseList.add(single_leg_hip_down)
        val split_jump =
            ExerciseModel(
                1, "Split Jump", "split_jump.json",
                isCompleted = false,
                isSelected = false
            )
        exerciseList.add(split_jump)
        val squat_kick =
            ExerciseModel(
                1, "Squat Kick", "squat_kicks.json",
                isCompleted = false,
                isSelected = false
            )
        exerciseList.add(squat_kick)
        val squat_reach =
            ExerciseModel(
                1, "Squat Reach", "squat_reach.json",
                isCompleted = false,
                isSelected = false
            )
        exerciseList.add(squat_reach)
        val step_up_on_chair =
            ExerciseModel(
                1, "Step up on chair", "step_up_chair.json",
                isCompleted = false,
                isSelected = false
            )
        exerciseList.add(step_up_on_chair)
        val pushup =
            ExerciseModel(
                1, "Pushup", "pushup.json",
                isCompleted = false,
                isSelected = false
            )
        exerciseList.add(pushup)
        val cobras =
            ExerciseModel(
                1, "Cobra", "cobra.json",
                isCompleted = false,
                isSelected = false
            )
        exerciseList.add(cobras)
        val frogPress =
            ExerciseModel(
                1, "Frog Press", "frog_press.json",
                isCompleted = false,
                isSelected = false
            )
        exerciseList.add(frogPress)

        val jumpingJack =
            ExerciseModel(
                1, "Jumping Jack", "jumping_jack.json",
                isCompleted = false,
                isSelected = false
            )
        exerciseList.add(jumpingJack)
        val lunge =
            ExerciseModel(
                1, "Lunge", "lunge.json",
                isCompleted = false,
                isSelected = false
            )
        exerciseList.add(lunge)
        val reverse_crunches =
            ExerciseModel(
                1, "Reverse Crunches", "reverse_crunches.json",
                isCompleted = false,
                isSelected = false
            )
        exerciseList.add(reverse_crunches)
        val seated_abs_circles =
            ExerciseModel(
                1, "Seated Abs Circles", "seated_abs_circles.json",
                isCompleted = false,
                isSelected = false
            )
        exerciseList.add(seated_abs_circles)


        return exerciseList
    }
}