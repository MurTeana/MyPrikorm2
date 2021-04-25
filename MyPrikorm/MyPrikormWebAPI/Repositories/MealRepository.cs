using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using MyPrikormWebAPI.Interfaces;
using MyPrikormWebAPI.Model.DB.Context;
using MyPrikormWebAPI.Model.DB.Entities;

namespace MyPrikormWebAPI.Repositories
{
    public class MealRepository : IRepositoryMeal
    {
        private ApplicationContext db;

        public MealRepository(ApplicationContext context)
        {
            db = context;
        }

        public async Task<List<Meal>> GetAll()
        {
            return await db.Meals.ToListAsync();
        }

        public async Task<Meal> Get(int id)
        {
            return await db.Meals.FirstOrDefaultAsync(x => x.Id == id);
        }

        public Meal Create(Meal meal)
        {
            if (meal != null)
            {
                db.Meals.Add(meal);
                Save();
            }

            return meal;
        }

        public Meal Update(Meal meal)
        {
            if (!db.Meals.Any(x => x.Id == meal.Id))
            {
                return null;
            }

            db.Update(meal);
            Save();
            return meal;
        }

        public Meal Delete(int id)
        {
            Meal meal = db.Meals.FirstOrDefault(x => x.Id == id);
            if (meal == null)
            {
                return meal;
            }

            db.Meals.Remove(meal);
            Save();
            return meal;
        }

        public void Save()
        {
            db.SaveChanges();
        }
    }
}
